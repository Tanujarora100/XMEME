package com.crio.starter.service;

import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.DuplicateMemeException;
import com.crio.starter.exceptions.InvalidRequestException;
import com.crio.starter.exceptions.MemeDoesNotExistException;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.XMemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class XMemeServiceImpl implements XMemeService {
    @Autowired
    private XMemeRepository xMemeRepository;
    @Autowired
    private ModelMapper mapper;

    private boolean nullValidationLogic(RequestDto requestDto) {
        return requestDto.getCaption() != null && requestDto.getName() != null && requestDto.getURL() != null;
    }

    @Override
    public boolean isRedudant(String name, String url, String caption) {
        return xMemeRepository.existsBynameAndURLAndCaption(name, url, caption);
    }

    public boolean isRedundant(RequestDto requestDto) {
        return xMemeRepository.existsBynameAndURLAndCaption(
                requestDto.getName(),
                requestDto.getURL(),
                requestDto.getCaption()
        );
    }

    @Override
    public ResponseDto save(RequestDto requestDto) {
        if (isRedundant(requestDto)) {
            throw new DuplicateMemeException("Duplicate Meme");
        }
        if (!nullValidationLogic(requestDto)) {
            throw new InvalidRequestException("Invalid Request");
        }
        Meme mappedMeme = mapper.map(requestDto, Meme.class);
        mappedMeme.setCreationDate(LocalDateTime.now());
        xMemeRepository.save(mappedMeme);
        return mapper.map(mappedMeme, ResponseDto.class);
    }

    @Override
    public ResponseDto getMemeById(String id) {
        Optional<Meme> meme = xMemeRepository.findById(id);
        if (meme.isEmpty()) {
            return null; // Return null instead of throwing MemeDoesNotExistException
        } else {
            return mapper.map(meme.get(), ResponseDto.class);
        }
    }

    @Override
    public List<ResponseDto> findAll() {
        List<Meme> all = xMemeRepository.findAll();
        return all.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ResponseDto mapToResponseDto(Meme meme) {
        return mapper.map(meme, ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getLatestMemes() {

        int pageSize = 100;
        Sort sort = Sort.by(Sort.Order.desc("creationDate"));
        Pageable pageable = PageRequest.of(0, pageSize, sort);
        Page<Meme> memePage = xMemeRepository.findAll(pageable);

        if (memePage.isEmpty()) {
            throw  new MemeDoesNotExistException("Meme does not exist");
        }

        List<Meme> memeList = memePage.getContent();
        List<ResponseDto> responseDtos = new ArrayList<>();

        for (Meme meme : memeList) {
            ResponseDto responseDto = mapper.map(meme, ResponseDto.class);
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }
}
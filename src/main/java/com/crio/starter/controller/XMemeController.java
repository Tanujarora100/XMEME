package com.crio.starter.controller;


import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.DuplicateMemeException;
import com.crio.starter.exceptions.InvalidRequestException;
import com.crio.starter.exceptions.MemeDoesNotExistException;
import com.crio.starter.exchange.GetMemeByIdRequest;
import com.crio.starter.exchange.IDResponse;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.XMemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController

public class XMemeController {
    @Autowired
    private XMemeService xMemeService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/memes/")
    public ResponseEntity<IDResponse> createMeme(@RequestBody RequestDto requestDto) {
        try{
            ResponseDto savedEntityDto= xMemeService.save(requestDto);
            IDResponse idResponse= mapper.map(savedEntityDto,IDResponse.class);
            return ResponseEntity.ok().body(idResponse);
        }
        catch(DuplicateMemeException e)
        {
            return ResponseEntity.status(409).build();
        }
        catch(InvalidRequestException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/memes/{id}")
    public ResponseEntity<Meme> getMemeByID(GetMemeByIdRequest getMemeByIdRequest) {
        String id = getMemeByIdRequest.getId();
        ResponseDto memeById = xMemeService.getMemeById(id);
        if(memeById==null)
            return ResponseEntity.notFound().build();
        Meme mappedMeme= mapper.map(memeById,Meme.class);
        return ResponseEntity.ok().body(mappedMeme);
    }



    @GetMapping("memes/")
    public ResponseEntity<List<ResponseDto>> getLatestMemes() {
        List<ResponseDto> listOfMemes = null;
        try {
            listOfMemes = xMemeService.getLatestMemes();
        } catch (MemeDoesNotExistException e) {
            // Handle the exception and return an empty list
            listOfMemes = new ArrayList<>();
        }
        return ResponseEntity.ok().body(listOfMemes);
    }

    @GetMapping("/allMemes")
    public ResponseEntity<List<ResponseDto>> getAll() {

        List<ResponseDto> all = xMemeService.findAll();
        if(all.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(all);
    }

    }


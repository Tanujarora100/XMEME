package com.crio.starter.service;

import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.MemeDoesNotExistException;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.XMemeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class XMemeServiceTest {
    @InjectMocks
    private XMemeService xMemeService;
    @InjectMocks
    private XMemeRepository xMemeRepository;

    @BeforeEach
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testSave() {
//        RequestDto dto = RequestDto.builder().name("Tanuj").URL("https://example.com/meme.jpg").caption("FunnyMeme").build();
//        Meme mappedMeme= new Meme();
//        mappedMeme.setId(UUID.randomUUID().toString());
//        mappedMeme.setName("Tanuj");
//        mappedMeme.setURL("https://example.com/meme.jpg");
//        mappedMeme.setCaption("FunnyMeme");
//        when(xMemeRepository.existsBynameAndURLAndCaption(("Tanuj"), ("https://example.com/meme.jpg"), ("FunnyMeme"))).thenReturn(false);
////        when(xMemeRepository.save(any(Meme.class))).thenReturn(mappedMeme);
//
//        ResponseDto responseDto = xMemeService.save(requestDto);
//
//        assertNotNull(responseDto);
//        assertEquals(mappedMeme.getId(), responseDto.getId());
//    }

    @Test
    public void testGetMemeById() {
        String id = UUID.randomUUID().toString();
        Meme meme = new Meme();
        meme.setId(id);
        meme.setName("John Doe");
        meme.setCaption("Funny Meme");
        meme.setURL("https://example.com/meme.jpg");
        meme.setCreationDate(LocalDateTime.now());

        when(xMemeRepository.findById(eq(id))).thenReturn(Optional.of(meme));

        ResponseDto responseDto = xMemeService.getMemeById(id);

        assertNotNull(responseDto);
        assertEquals(id, responseDto.getId());
    }

    @Test
    public void testGetMemeByIdMemeDoesNotExist() {
        String id = UUID.randomUUID().toString();

        when(xMemeRepository.findById(eq(id))).thenReturn(Optional.empty());

        assertThrows(MemeDoesNotExistException.class, () -> xMemeService.getMemeById(id));
    }
    @Test
    public void testFindAll() {
        List<Meme> memeList = new ArrayList<>();
        Meme meme1 = new Meme();
        meme1.setId(UUID.randomUUID().toString());
        meme1.setName("John Doe");
        meme1.setCaption("Funny Meme 1");
        meme1.setURL("https://example.com/meme1.jpg");
        meme1.setCreationDate(LocalDateTime.now());

        Meme meme2 = new Meme();
        meme2.setId(UUID.randomUUID().toString());
        meme2.setName("Jane Smith");
        meme2.setCaption("Funny Meme 2");
        meme2.setURL("https://example.com/meme2.jpg");
        meme2.setCreationDate(LocalDateTime.now());

        memeList.add(meme1);
        memeList.add(meme2);

        when(xMemeRepository.findAll()).thenReturn(memeList);

        List<ResponseDto> responseDtos = xMemeService.findAll();

        assertNotNull(responseDtos);
        assertEquals(2, responseDtos.size());
    }

    @Test
    public void testFindAllNoMemesFound() {
        when(xMemeRepository.findAll()).thenReturn(new ArrayList<>());

        List<ResponseDto> responseDtos = xMemeService.findAll();

        assertNotNull(responseDtos);
        assertEquals(0, responseDtos.size());
    }

}

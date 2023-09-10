package com.crio.starter.service;

import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;

import java.util.List;

public interface XMemeService {
    public ResponseDto save(RequestDto requestDto);

    public ResponseDto getMemeById(String id);
    List<ResponseDto> findAll();


    public boolean isRedudant(String name, String url, String caption);

    public List<ResponseDto> getLatestMemes();

}

package com.example.exchangeapp.service;

import com.example.exchangeapp.domain.BitMexCandlestickEntity;
import com.example.exchangeapp.dto.BitMexCandlestickDataDto;
import com.example.exchangeapp.dto.BitMexCandlestickDto;
import com.example.exchangeapp.dto.PageRequestDto;
import com.example.exchangeapp.dto.PageResponseDto;
import com.example.exchangeapp.mapper.BitMexCandlestickMapper;
import com.example.exchangeapp.repository.BitMexRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BitMexExchangeService {

    private final ExchangeServiceImpl exchangeService;
    private final BitMexRepository bitMexRepository;
    private final ObjectMapper objectMapper;
    private final BitMexCandlestickMapper bitMexCandlestickMapper;

    private static final String BITMEX_URL = "wss://www.bitmex.com/realtime?subscribe=";
    private static final String TOPIC_TRADE_BIN = "tradeBin1m";
    private static final String PAIR_1 = ":XBTUSD";
    private static final String PAIR_2 = ":ETHUSD";
    private static final String ACTION = "partial";

    @Scheduled(fixedDelay = 60000)
    public void getExchangeCandlestick() {
        exchangeService.getExchangeCandlestick(BITMEX_URL + TOPIC_TRADE_BIN + PAIR_1 + "," +
                TOPIC_TRADE_BIN + PAIR_2, this::processReceivedMessage);
    }

    protected void processReceivedMessage(String message) {
        if (message.contains(TOPIC_TRADE_BIN) && message.contains(ACTION)) {
            BitMexCandlestickDataDto dataDto = null;
            try {
                dataDto = objectMapper.readValue(message, BitMexCandlestickDataDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            Optional.ofNullable(dataDto)
                    .ifPresent(data -> {
                        data.getData()
                                .stream()
                                .map(bitMexCandlestickMapper::mapToBitMexCandlestickEntity)
                                .forEach(bitMexRepository::save);
                    });
        }
    }

    public List<BitMexCandlestickDto> findLastData() {
        List<BitMexCandlestickEntity> bitMexCandlestickEntities = bitMexRepository.findLastData();
        if (bitMexCandlestickEntities != null) {
            return bitMexCandlestickEntities.stream()
                    .map(bitMexCandlestickMapper::mapToBitMexCandlestickDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<BitMexCandlestickDto> findLastDataBTCUSD() {
        List<BitMexCandlestickEntity> bitMexCandlestickEntities = bitMexRepository.findLastDataBTC();
        if (bitMexCandlestickEntities != null) {
            Collections.reverse(bitMexCandlestickEntities);
            return bitMexCandlestickEntities.stream()
                    .map(bitMexCandlestickMapper::mapToBitMexCandlestickDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<BitMexCandlestickDto> findLastDataETHUSD() {
        List<BitMexCandlestickEntity> bitMexCandlestickEntities = bitMexRepository.findLastDataETH();
        if (bitMexCandlestickEntities != null) {
            Collections.reverse(bitMexCandlestickEntities);
            return bitMexCandlestickEntities.stream()
                    .map(bitMexCandlestickMapper::mapToBitMexCandlestickDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<BitMexCandlestickDto> findAllData(PageRequestDto pageRequestDto) {

        Page<BitMexCandlestickEntity> bitMexCandlestickEntities = bitMexRepository.findAll(PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize()));
        if (bitMexCandlestickEntities != null) {
            return bitMexCandlestickEntities.getContent().stream()
                    .map(bitMexCandlestickMapper::mapToBitMexCandlestickDto)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public PageResponseDto findLastPageNumber(Integer pageSize) {
        Integer lastPageNumber = bitMexRepository.findCountOfAllRecords() / pageSize;
        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(lastPageNumber);
        return pageResponseDto;
    }

}

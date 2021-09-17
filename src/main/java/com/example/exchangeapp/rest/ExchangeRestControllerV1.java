package com.example.exchangeapp.rest;

import com.example.exchangeapp.dto.BitMexCandlestickDto;
import com.example.exchangeapp.dto.PageRequestDto;
import com.example.exchangeapp.dto.PageResponseDto;
import com.example.exchangeapp.service.BitMexExchangeService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Exchange endpoints")
@RestController
@RequestMapping(value = "/api/v1/exchange")
@AllArgsConstructor
public class ExchangeRestControllerV1 {

    private final BitMexExchangeService exchangeService;

    @ApiOperation(value = "Get last data BTCUSD, ETHUSD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Last data BTCUSD, ETHUSD successfully received"),
            @ApiResponse(code = 204, message = "Data not found")
    })
    @GetMapping("/permit-all/last-data-btcusd-ethusd")
    public ResponseEntity<List<BitMexCandlestickDto>> findLastData() {
        List<BitMexCandlestickDto> bitMexCandlestickDtoList = exchangeService.findLastData();
        if (bitMexCandlestickDtoList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bitMexCandlestickDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get last data BTCUSD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Last data BTCUSD successfully received"),
            @ApiResponse(code = 204, message = "Data not found"),
            @ApiResponse(code = 403, message = "Access denied, authorization required")
    })
    @GetMapping("/auth/last-data-btcusd")
    public ResponseEntity<List<BitMexCandlestickDto>> findLastDataBTCUSD() {
        List<BitMexCandlestickDto> bitMexCandlestickDtoList = exchangeService.findLastDataBTCUSD();
        if (bitMexCandlestickDtoList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bitMexCandlestickDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get last data ETHUSD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Last data ETHUSD successfully received"),
            @ApiResponse(code = 204, message = "Data not found"),
            @ApiResponse(code = 403, message = "Access denied, authorization required")
    })
    @GetMapping("/auth/last-data-ethusd")
    public ResponseEntity<List<BitMexCandlestickDto>> findLastDataETHUSD() {
        List<BitMexCandlestickDto> bitMexCandlestickDtoList = exchangeService.findLastDataETHUSD();
        if (bitMexCandlestickDtoList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bitMexCandlestickDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all data BTCUSD, ETHUSD", authorizations = {@Authorization(value = "Bearer Token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All data BTCUSD, ETHUSD successfully received"),
            @ApiResponse(code = 204, message = "Data not found"),
            @ApiResponse(code = 403, message = "Access denied, authorization required")
    })
    @PostMapping("/auth/all-data-btcusd-ethusd")
    public ResponseEntity<List<BitMexCandlestickDto>> findAllData(@ApiParam(value = "Pagination request", required = true)
                                                                  @RequestBody PageRequestDto pageRequestDto) {
        List<BitMexCandlestickDto> bitMexCandlestickDtoList = exchangeService.findAllData(pageRequestDto);
        if (bitMexCandlestickDtoList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bitMexCandlestickDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Get last page number of data base", authorizations = {@Authorization(value = "Bearer Token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Last page number successfully received"),
            @ApiResponse(code = 204, message = "Data not found"),
            @ApiResponse(code = 403, message = "Access denied, authorization required")
    })
    @GetMapping("/auth/last-page-bumber")
    public ResponseEntity<PageResponseDto> findLastPageNumber(@RequestParam(required = true) String size) {
        PageResponseDto pageResponseDto = exchangeService.findLastPageNumber(Integer.parseInt(size));
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }
}

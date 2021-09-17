package com.example.exchangeapp.repository;

import com.example.exchangeapp.domain.BitMexCandlestickEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitMexRepository extends JpaRepository<BitMexCandlestickEntity, Long> {

    @Query(value = "SELECT * FROM ohcl WHERE (symbol, created) IN (SELECT symbol, max(created) FROM ohcl GROUP BY symbol)", nativeQuery = true)
    List<BitMexCandlestickEntity> findLastData();

    @Query(value = "SELECT * FROM (SELECT * FROM ohcl ORDER BY id DESC LIMIT 60) t WHERE t.symbol = 'XBTUSD'", nativeQuery = true)
    List<BitMexCandlestickEntity> findLastDataBTC();

    @Query(value = "SELECT * FROM (SELECT * FROM ohcl ORDER BY id DESC LIMIT 60) t WHERE t.symbol = 'ETHUSD'", nativeQuery = true)
    List<BitMexCandlestickEntity> findLastDataETH();

    Page<BitMexCandlestickEntity> findAll(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM ohcl", nativeQuery = true)
    Integer findCountOfAllRecords();
}

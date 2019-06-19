package com.ascending.project.repository;

import com.ascending.project.domain.Bill;

import java.util.List;

public interface BillDao {
    Bill save (Bill bill);
    List<Bill> findAll();
    Bill findByIdEager(Long id);
    Bill findById(Long id);
}

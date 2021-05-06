package br.com.leomanzini.dsvendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leomanzini.dsvendas.dto.SaleDTO;
import br.com.leomanzini.dsvendas.dto.SaleSuccessDTO;
import br.com.leomanzini.dsvendas.dto.SaleSumDTO;
import br.com.leomanzini.dsvendas.obj.Sale;
import br.com.leomanzini.dsvendas.repositories.SaleRepository;
import br.com.leomanzini.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Transactional(readOnly = true)
	public Page<SaleDTO> findAll(Pageable pageable) {
		sellerRepository.findAll();
		Page<Sale> saleList = repository.findAll(pageable);
		return saleList.map(x -> new SaleDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<SaleSumDTO> amountGroupedBySeller() {
		return repository.amountGroupedBySeller();
	}
	
	@Transactional(readOnly = true)
	public List<SaleSuccessDTO> successGroupedBySeller() {
		return repository.successGroupedBySeller();
	}
}

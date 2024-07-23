package com.example.demo.service.impl;

import com.example.demo.model.dto.AddSupplierDTO;
import com.example.demo.model.dto.SupplierDTO;
import com.example.demo.model.entity.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SupplierDTO> getAllPartners() {
        return this.supplierRepository.findAll().stream()
                .map(supplier -> this.modelMapper.map(supplier, SupplierDTO.class))
                .toList();
    }

    @Override
    public SupplierDTO addPartner(AddSupplierDTO addPartnerDTO) {
        Supplier supplier = this.modelMapper.map(addPartnerDTO, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);

        return this.modelMapper.map(supplier, SupplierDTO.class);
    }

    @Override
    public void deletePartner(Long id) {
        this.supplierRepository.deleteById(id);
    }
}

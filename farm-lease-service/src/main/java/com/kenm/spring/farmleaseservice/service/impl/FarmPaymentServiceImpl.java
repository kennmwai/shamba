package com.kenm.spring.farmleaseservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.kenm.spring.farmleaseservice.dto.FarmPaymentDTO;
import com.kenm.spring.farmleaseservice.entity.FarmLease;
import com.kenm.spring.farmleaseservice.entity.FarmPayment;
import com.kenm.spring.farmleaseservice.mapper.PaymentMapper;
import com.kenm.spring.farmleaseservice.repository.FarmPaymentRepository;
import com.kenm.spring.farmleaseservice.service.FarmPaymentService;

@Service
public class FarmPaymentServiceImpl implements FarmPaymentService {

    @Autowired
	private FarmPaymentRepository paymentRepository;

	@Autowired
	private PaymentMapper paymentMapper;

    @Override
    public long count() {
        return paymentRepository.count();
    }

    @Override
    public boolean exists(@NonNull Long id) {
        return paymentRepository.existsById(id);
    }

    @Override
    public void deleteAll() {
        paymentRepository.deleteAll();
    }

    @Override
    public void delete(@NonNull Long id) {
        if (!exists(id)) {
            throw new IllegalArgumentException("Farm Payment with id " + id + " not found");
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(@NonNull Iterable<Long> ids) {
        paymentRepository.deleteAllById(ids);
    }

    @Override
    public List<FarmPaymentDTO> getAllPayments() {
        List <FarmPayment> payments = paymentRepository.findAll();
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

    @Override
    public FarmPaymentDTO getPayment(@NonNull Long id) {
        FarmPayment payment = paymentRepository.findById(id).orElse(null);
        return paymentMapper.toFarmPaymentDTO(payment);
    }

    @Override
    public List<FarmPaymentDTO> getPaymentsByIds(@NonNull Iterable<Long> ids) {
        List <FarmPayment> payments = paymentRepository.findAllById(ids);
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

    @Override
    public List<FarmPaymentDTO> getFarmPaymentByStatus(String status) {
        List <FarmPayment> payments = paymentRepository.findByStatus(status);
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

    @Override
    public List<FarmPaymentDTO> getFarmPaymentByMethod(String method) {
        List <FarmPayment> payments = paymentRepository.findByMethod(method);
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

    @Override
    public List<FarmPaymentDTO> getFarmPaymentByReceipt(Long receipt) {
        List <FarmPayment> payments = paymentRepository.findByReceipt(receipt);
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

    @Override
    public FarmPaymentDTO createPayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease) {
        FarmPayment payment = paymentMapper.toFarmPayment(farmPaymentDTO);
        payment.setFarmLease(lease);
        payment = paymentRepository.save(payment);
        return paymentMapper.toFarmPaymentDTO(payment);
    }

    @Override
    public FarmPaymentDTO updatePayment(FarmPaymentDTO farmPaymentDTO, FarmLease lease) {
        FarmPayment payment = paymentMapper.toFarmPayment(farmPaymentDTO);
        payment.setFarmLease(lease);
        payment = paymentRepository.save(payment);
        return paymentMapper.toFarmPaymentDTO(payment);
    }
    @Override
    public List<FarmPaymentDTO> updatePayments(List<FarmPaymentDTO> farmPaymentDTO, FarmLease lease) {
        List <FarmPayment> payments = paymentMapper.toFarmPayments(farmPaymentDTO);
        payments.forEach(payment -> payment.setFarmLease(lease));
        paymentRepository.saveAll(payments);
        return paymentMapper.toFarmPaymentDTOs(payments);
    }

}

package com.jilds.labseq.service.impl;

import com.jilds.labseq.exception.LabseqException;
import com.jilds.labseq.service.LabseqService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class LabseqServiceImpl implements LabseqService {

    @Override
    public BigInteger doTheMath(Integer value) {
        if (value < 0) {
            throw new LabseqException("The index value must be a non-negative integer");
        }

        if (value == 0 || value == 2) {
            return BigInteger.ZERO;
        }

        if (value == 1 || value == 3) {
            return BigInteger.ONE;
        }

        BigInteger result = doTheMath(value - 4).add(doTheMath(value - 3));

        return result;
    }
}

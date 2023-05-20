package com.jilds.labseq.service.impl;

import com.jilds.labseq.exception.LabseqException;
import com.jilds.labseq.service.LabseqService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class LabseqServiceImpl implements LabseqService {

    private final Map<Integer, BigInteger> localCache = new HashMap<>();

    @Override
    public BigInteger doTheMath(Integer value) {

        if (value < 0) {
            throw new LabseqException("The value must be equal or greater then 0", HttpStatus.BAD_REQUEST);
        }

        if (value == 0 || value == 2) {
            return BigInteger.ZERO;
        }

        if (value == 1 || value == 3) {
            return BigInteger.ONE;
        }

        if (localCache.containsKey(value)){
            return localCache.get(value);
        }

        BigInteger result = doTheMath(value - 4).add(doTheMath(value - 3));

        localCache.put(value, result);

        return result;
    }
}

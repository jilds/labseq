package com.jilds.labseq.controller;

import com.jilds.labseq.service.LabseqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Tag(name = "LabSeq")
@RestController
@RequestMapping("/api/labseq")
public class LabseqController {

    private final LabseqService labseqService;

    public LabseqController(LabseqService labseqService){
        this.labseqService = labseqService;
    }

    @Operation(summary = "Calculate the value of labseq(n)",
            description = "Returns the value of labseq according to the given value for n, where n must be an integer plus or equal to 0")
    @GetMapping(value = "/{n}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BigInteger> getResult(@PathVariable(value = "n") Integer value) {
        BigInteger result = labseqService.doTheMath(value);
        return ResponseEntity.ok(result);
    }

}

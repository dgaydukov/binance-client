package com.example.binance.services;

import com.example.binance.config.ApiConstants;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class SignatureService {
    public String sign(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance(ApiConstants.ALGO);
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), ApiConstants.ALGO);
            sha256_HMAC.init(secret_key);
            return HexUtils.toHexString(sha256_HMAC.doFinal(data.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new RuntimeException("Failed to encode");
        }
    }
}
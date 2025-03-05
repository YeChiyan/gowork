package com.elk.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@RestController
@RequestMapping("/api/compression-test")
public class CompressionTestController {

    // 测试数据生成端点
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> getTestData(
            @RequestParam(defaultValue = "1000") int count,
            @RequestHeader(HttpHeaders.ACCEPT_ENCODING) String acceptEncoding) throws IOException {

        // 生成测试数据
        String jsonData = generateTestData(count);
        byte[] data = jsonData.getBytes(StandardCharsets.UTF_8);

        // 根据请求头决定是否压缩
        if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (GZIPOutputStream gzipOS = new GZIPOutputStream(bos)) {
                gzipOS.write(data);
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_ENCODING, "gzip")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bos.toByteArray());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data);
    }

    // 压缩数据接收测试
    @PostMapping("/receive")
    public ResponseEntity<String> receiveCompressedData(
            @RequestBody byte[] compressedData,
            @RequestHeader(HttpHeaders.CONTENT_ENCODING) String contentEncoding) {

        try {
            String receivedData = decompress(compressedData);
            return ResponseEntity.ok()
                    .body("接收成功，数据长度: " + receivedData.length());
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("解压失败: " + e.getMessage());
        }
    }

    // 数据生成方法
    private String generateTestData(int count) {
        List<ShipData> dataList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dataList.add(new ShipData(
                i,
                35.123456 + i * 0.0001,
                120.987654 - i * 0.0001,
                System.currentTimeMillis()
            ));
        }
        return "{\"ships\":" + dataList + "}";
    }

    // GZIP解压方法
    private String decompress(byte[] compressedData) throws IOException {
        try (java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(compressedData);
             java.util.zip.GZIPInputStream gzipIS = new java.util.zip.GZIPInputStream(bis);
             java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipIS.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            return new String(bos.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    // 数据模型
    static class ShipData {
        private int id;
        private double lat;
        private double lon;
        private long timestamp;

        public ShipData(int id, double lat, double lon, long timestamp) {
            this.id = id;
            this.lat = lat;
            this.lon = lon;
            this.timestamp = timestamp;
        }

        // getters 保证JSON序列化
        public int getId() { return id; }
        public double getLat() { return lat; }
        public double getLon() { return lon; }
        public long getTimestamp() { return timestamp; }
    }
} 
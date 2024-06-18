package com.example.controller;

import com.example.entity.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @Value("${spring.data.mongodb.host}")
    private String mongo;

    @PostMapping("/set")
    public ResponseEntity<String> setValue(@RequestParam String key, @RequestParam String value) {
        return ResponseEntity.ok(redisUtil.set(key, value));
    }

    @GetMapping("/key/{key}")
    public ResponseEntity<Object> getValueByKey(@PathVariable String key) {
        return ResponseEntity.ok(redisUtil.get(key));
    }

    @DeleteMapping("/keys")
    public ResponseEntity<Void> delValuesByKeys(@RequestBody List<String> keys) {
        redisUtil.del(keys.toArray(new String[0]));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/application/mongo")
    public ResponseEntity<Object> getValueByApplication() {
        return ResponseEntity.ok(mongo);
    }

}

package com.hamitmizrak.ui.rest.impl;


import com.hamitmizrak.bean.AdminDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class RestData {

    // http://localhost:8080/api/v1/rest/object2
    @GetMapping("/rest/object2")
    public AdminDto getRest2() {
        AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("adı").adminSurname("soyad").build();
        return adminDto;
    }

    //Default: json var biz tekrardan produces yazmak zorunda değiliz.
    // http://localhost:8080/api/v1/rest/object3
    @GetMapping(value = "/rest/object3", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminDto getRest3() {
        AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("adı").adminSurname("soyad").build();
        return adminDto;
    }


    //Default: json var biz tekrardan produces yazmak zorunda değiliz.
    // http://localhost:8080/api/v1/rest/object4
    @GetMapping(value = "/rest/object4", produces = MediaType.APPLICATION_XML_VALUE)
    public AdminDto getRest4() {
        AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("adı").adminSurname("soyad").build();
        return adminDto;
    }

    //Default: json var biz tekrardan produces yazmak zorunda değiliz.
    // http://localhost:8080/api/v1/rest/object5
    @GetMapping("/rest/object5")
    public List<AdminDto> getRest5() {
        List<AdminDto> list = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        for (int i = 1; i < 10; i++) {
            list.add(AdminDto.builder().adminId(Long.valueOf(i)).adminName("adı: " + i).adminSurname("soyad: " + uuid).build());
        }
        return list;
    }


    //PathVariable
    // http://localhost:8080/api/v1/rest/object6/
    // http://localhost:8080/api/v1/rest/object6/44
    @GetMapping({"/rest/object6", "/rest/object6/{id}"})
    public AdminDto getRest6(@PathVariable(name = "id", required = false) Long idim) {
        AdminDto adminDto = AdminDto.builder().adminId(idim).adminName("adı").adminSurname("soyad").build();
        return adminDto;
    }


    //RequestParam
    // http://localhost:8080/api/v1/rest/object7?adi=Hamit
    @GetMapping(value = "/rest/object7")
    public AdminDto getRest7( @RequestParam(name="adi") String name) {
        AdminDto adminDto = AdminDto.builder().adminId(0L).adminName(name).adminSurname("soyad").build();
        return adminDto;
    }


    //Default: json var biz tekrardan produces yazmak zorunda değiliz.
    // http://localhost:8080/api/v1/rest/object8
    @GetMapping(value = "/rest/object8")
    public ResponseEntity<AdminDto>  getRest8() {
        AdminDto adminDto = AdminDto.builder().adminId(0L).adminName("adı").adminSurname("soyad").build();
        //return   ResponseEntity.ok(adminDto);/
        //return   ResponseEntity.ok().body(adminDto);
       // return   ResponseEntity.status(200).body(adminDto);
        return   ResponseEntity.status(HttpStatus.OK).body(adminDto);
    }



    //PathVariable
    // http://localhost:8080/api/v1/rest/object9/
    // http://localhost:8080/api/v1/rest/object9/44
    @GetMapping({"/rest/object9", "/rest/object9/{id}"})
    public  ResponseEntity<?> getRest9(@PathVariable(name = "id", required = false) Long idim) {
        AdminDto adminDto = AdminDto.builder().adminId(idim).adminName("adı").adminSurname("soyad").build();
        //id=null
        if(idim==null){
            log.error("404 Bulunmadı");
            return ResponseEntity.notFound().build();
        }else if(idim==0){
            log.error("400 Bad request");
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(adminDto) ;
    }
}

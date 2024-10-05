package com.javaweb.WebsiteRoomForRent.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BuildingController {
}

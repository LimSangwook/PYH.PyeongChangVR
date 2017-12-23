package com.module.vr.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;

import com.common.util.CommonWebUtils;

@Controller
public class VrSiteContentSpotController extends CommonWebUtils {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor message;

}

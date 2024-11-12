package com.softeem.controller.tj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.softeem.model.room.Player;
import com.softeem.services.PlayerService;

import io.swagger.annotations.ApiOperation;

@Controller
public class PlayerInfoController {
	@Autowired
	private PlayerService playerService;
	
    @ApiOperation("分页查询")
    @GetMapping("/getPage")
    @ResponseBody
    public List<Player> getPage(int num, int size) {

        PageHelper.startPage(num, size);
        List<Player> list = playerService.list();
        return list;
    }

}

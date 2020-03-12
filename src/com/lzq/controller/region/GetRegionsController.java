package com.lzq.controller.region;

import com.alibaba.fastjson.JSONObject;
import com.lzq.bean.CnRegion;
import com.lzq.service.RegionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getRegions.do")
public class GetRegionsController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        CnRegion cnRegion = RegionService.findByCode(code);
        JSONObject json = new JSONObject();
        if(cnRegion.getLevel()==4){
            json.put("status",-1);
        }else{
            List<CnRegion> citys = RegionService.findByParentCode(code);
            json.put("citys",citys);
            json.put("status",200);
            json.put("level",cnRegion.getLevel()+1);
        }
        resp.getWriter().write(json.toJSONString());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

package com.admin.framework.component.logistics;

import lombok.Data;

import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 11:39 2019\9\19 0019
 */
@Data
public class LogisticsResponse {


    private String message;
    private String state;
    private String status;
    private String condition;
    private String ischeck;
    private String com;
    private String nu;
    private List<ResultData> data;

    @Data
    public static class ResultData{
        private String context;
        private String time;
        private String ftime;
    }

}

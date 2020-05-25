package com.demo.util;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankCardClear {

    public static void main(String[] args) throws Exception{
        doClear();
//        fun3();
//        fun2();
    }

    public static final String URL = "";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final OkHttpClient CLIENT = new OkHttpClient();
    public static void doClear() throws Exception{
        List<String> lines = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/clear_temp"));
        for (String line : lines) {
            if (StringUtils.isEmpty(line)) {
                continue;
            }
            CardClearData cardClearData = build(line);
            Request request = new Request.Builder().url(URL)
                    .post(RequestBody.create(JSON, JSONObject.toJSONString(cardClearData))).build();
            Response resp = CLIENT.newCall(request).execute();
            if (! resp.isSuccessful()) {
                System.out.println("execute error");
            }
            System.out.println(JSONObject.toJSONString(cardClearData) +"=>"+ resp.body().string());
        }
    }

    public static CardClearData build(String line) {
        String[] arr = line.split(",");
        CardClearData res = new CardClearData();
        res.setBatch("test");
        res.setShopId(Integer.parseInt(arr[0].trim()));
        res.setCardNumber(arr[1].trim());
        res.setAccountName(arr[2].trim());
        res.setBankId(Integer.parseInt(arr[3].trim()));
        res.setBankBranchId(Integer.parseInt(arr[4].trim()));
        res.setAccountType(Integer.parseInt(arr[5].trim()));
        res.setCardId(64999);

//        res.setProvinceId(66211);
//        res.setCityId(1);
        return res;
    }

    public static void fun2() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/bank_info"));

        List<BankInfo> bankInfos = new ArrayList<>(lines.size());
        for (String line : lines) {
            bankInfos.add(JSONObject.parseObject(line, BankInfo.class));
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputs = input.split(",");

            Pair<Bank, Bank> bankPair = fetchBankInfo(bankInfos, inputs[0], inputs[1]);
            System.out.println(bankPair == null ? "no_match" : JSONObject.toJSONString(bankPair.getKey()) +"->"+ JSONObject.toJSONString(bankPair.getValue()));
        }
    }

    public static void fun3() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/bank_info"));

        List<BankInfo> bankInfos = new ArrayList<>(lines.size());
        for (String line : lines) {
            bankInfos.add(JSONObject.parseObject(line, BankInfo.class));
        }

        String prefix = "/Users/hugang/Desktop/银行卡清洗/";
        String noBranch = "no_branch";
        String noCardId = "no_card_id";
        String noBranchNoCardId = "no_branch_card_id";
        String fullInfo = "full_info";
        List<String> no_branch = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/no_branch"));
        List<String> no_card_id = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/no_card_id"));
        List<String> no_branch_card_id = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/no_branch_card_id"));
        List<String> full_info = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/full_info"));

        BufferedWriter bf1 = Files.newBufferedWriter(Paths.get(prefix+noBranch+"_clear"));
        BufferedWriter bf2 = Files.newBufferedWriter(Paths.get(prefix+noCardId+"_clear"));
        BufferedWriter bf3 = Files.newBufferedWriter(Paths.get(prefix+noBranchNoCardId+"_clear"));
        BufferedWriter bf4 = Files.newBufferedWriter(Paths.get(prefix+fullInfo+"_clear"));

        for (String l : no_branch) {
            String[] arr = l.split(",");
            Pair<Bank, Bank> bankPair = fetchBankInfo(bankInfos, arr[3], "1");

            String bankId = arr[3]+"_no_match";
            String branchId = "no_match";
            if (bankPair != null) {
                bankId = String.valueOf(bankPair.getKey().getId());
                branchId = String.valueOf(bankPair.getValue().getId());
            }
            String toWrite = arr[0]+","+arr[1]+","+arr[2]+","+bankId+","+branchId+","+arr[4]+","+arr[5];
            writeLine(bf1, toWrite);
        }

        for (String l : no_card_id) {
            String[] arr = l.split(",");
            Pair<Bank, Bank> bank = fetchBankInfo(bankInfos, arr[3], "1");
            Pair<Bank, Bank> branch = fetchBankInfo(bankInfos, arr[4], "2");

            String bankId = arr[3]+"_no_match";
            String branchId = arr[4]+"_no_match";
            if (bank != null) {
                bankId = String.valueOf(bank.getKey().getId());
            }
            if (branch != null) {
                branchId = String.valueOf(branch.getValue().getId());
            } else if (bank != null) {
                branchId = String.valueOf(bank.getValue().getId());
            }
            String toWrite = arr[0]+","+arr[1]+","+arr[2]+","+bankId+","+branchId+","+arr[5];
            writeLine(bf2, toWrite);
        }

        for (String l : no_branch_card_id) {
            String[] arr = l.split(",");
            Pair<Bank, Bank> bank = fetchBankInfo(bankInfos, arr[3], "1");

            String bankId = arr[3]+"_no_match";
            String branchId = "no_match";
            if (bank != null) {
                bankId = String.valueOf(bank.getKey().getId());
                branchId = String.valueOf(bank.getValue().getId());
            }
            String toWrite = arr[0]+","+arr[1]+","+arr[2]+","+bankId+","+branchId+","+arr[4];
            writeLine(bf3, toWrite);
        }

        for (String l : full_info) {
            String[] arr = l.split(",");
            Pair<Bank, Bank> bank = fetchBankInfo(bankInfos, arr[3], "1");
            Pair<Bank, Bank> branch = fetchBankInfo(bankInfos, arr[4], "2");

            String bankId = arr[3]+"_no_match";
            String branchId = arr[4]+"_no_match";
            if (bank != null) {
                bankId = String.valueOf(bank.getKey().getId());
            }
            if (branch != null) {
                branchId = String.valueOf(branch.getValue().getId());
            } else if (bank != null) {
                branchId = String.valueOf(bank.getValue().getId());
            }
            String toWrite = arr[0]+","+arr[1]+","+arr[2]+","+bankId+","+branchId+","+arr[5]+","+arr[6];
            writeLine(bf4, toWrite);
        }

        bf1.close();
        bf2.close();
        bf3.close();
        bf4.close();
    }

    private static Pair<Bank, Bank> fetchBankInfo(List<BankInfo> bankInfos, String key, String mode) throws Exception{

        if (mode.equals("1")) {
            for (BankInfo bankInfo : bankInfos) {
                if (bankInfo.getMpayBankVO().getName().contains(key.trim())) {
                    return new Pair<>(bankInfo.getMpayBankVO(), bankInfo.getBankVOs().get(0));
                }
            }
        }

        for (BankInfo bankInfo : bankInfos) {
            for (Bank bank : bankInfo.getBankVOs()) {
                if (bank.getName().equals(key.trim())) {
                    if (bankInfo.getMpayBankVO().getName().contains(key.trim())) {
                         return new Pair<>(bankInfo.getMpayBankVO(), bankInfo.getBankVOs().get(0));
                    }
                }
            }
        }

        return null;
    }


    public static void fun1() throws Exception {
        String prefix = "/Users/hugang/Desktop/银行卡清洗/";
        String noBranch = "no_branch";
        String noCardId = "no_card_id";
        String noBranchNoCardId = "no_branch_card_id";
        String fullInfo = "full_info";

        BufferedWriter bf1 = Files.newBufferedWriter(Paths.get(prefix+noBranch));
        BufferedWriter bf2 = Files.newBufferedWriter(Paths.get(prefix+noCardId));
        BufferedWriter bf3 = Files.newBufferedWriter(Paths.get(prefix+noBranchNoCardId));
        BufferedWriter bf4 = Files.newBufferedWriter(Paths.get(prefix+fullInfo));
        List<String> lines = Files.readAllLines(Paths.get("/Users/hugang/Desktop/银行卡清洗/card_clear"));

        lines.stream().filter(line -> ! StringUtils.isEmpty(line)).forEach(line -> {
            String[] arr = line.split(",");
            if (StringUtils.isEmpty(arr[4]) && (arr.length < 7 || StringUtils.isEmpty(arr[6]))) {
                writeLine(bf3, toStr(arr));
            } else if (StringUtils.isEmpty(arr[4])) {
                writeLine(bf1, toStr(arr));
            } else if (arr.length < 7 || StringUtils.isEmpty(arr[6])) {
                writeLine(bf2, toStr(arr));
            } else {
                writeLine(bf4, toStr(arr));
            }
        });

        bf1.close();
        bf2.close();
        bf3.close();
        bf4.close();
    }

    private static String toStr(String[] arr) {
        StringBuilder res = new StringBuilder();
        for (String str : arr) {
            if (!StringUtils.isEmpty(str)) {
                res.append(str).append(",");
            }
        }

        return res.substring(0, res.length()-1);
    }

    private static void writeLine(BufferedWriter writer, String line) {
        try {
            writer.write(line+System.lineSeparator());
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static class Bank {
        private Integer id;

        private String name;

        public Integer getId() {
            return id;
        }

        public Bank setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Bank setName(String name) {
            this.name = name;
            return this;
        }
    }

    private static class BankInfo {
        private Bank mpayBankVO;
        private List<Bank> bankVOs;

        public Bank getMpayBankVO() {
            return mpayBankVO;
        }

        public BankInfo setMpayBankVO(Bank mpayBankVO) {
            this.mpayBankVO = mpayBankVO;
            return this;
        }

        public List<Bank> getBankVOs() {
            return bankVOs;
        }

        public BankInfo setBankVOs(List<Bank> bankVOs) {
            this.bankVOs = bankVOs;
            return this;
        }
    }

    public static class CardClearData{
        private String batch;

        private Integer cardId;

        private String cardNumber;

        private String accountName;

        private Integer bankId;

        private Integer bankBranchId;

        private Integer shopId;

        private Integer accountType;

        private Integer provinceId;

        private Integer cityId;

        public Integer getProvinceId() {
            return provinceId;
        }

        public CardClearData setProvinceId(Integer provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public Integer getCityId() {
            return cityId;
        }

        public CardClearData setCityId(Integer cityId) {
            this.cityId = cityId;
            return this;
        }

        public String getBatch() {
            return batch;
        }

        public CardClearData setBatch(String batch) {
            this.batch = batch;
            return this;
        }

        public Integer getCardId() {
            return cardId;
        }

        public CardClearData setCardId(Integer cardId) {
            this.cardId = cardId;
            return this;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public CardClearData setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public String getAccountName() {
            return accountName;
        }

        public CardClearData setAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Integer getBankId() {
            return bankId;
        }

        public CardClearData setBankId(Integer bankId) {
            this.bankId = bankId;
            return this;
        }

        public Integer getBankBranchId() {
            return bankBranchId;
        }

        public CardClearData setBankBranchId(Integer bankBranchId) {
            this.bankBranchId = bankBranchId;
            return this;
        }

        public Integer getShopId() {
            return shopId;
        }

        public CardClearData setShopId(Integer shopId) {
            this.shopId = shopId;
            return this;
        }

        public Integer getAccountType() {
            return accountType;
        }

        public CardClearData setAccountType(Integer accountType) {
            this.accountType = accountType;
            return this;
        }
    }
}

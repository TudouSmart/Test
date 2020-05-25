package com.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThriftRespFormat {


    public static void main(String[] args) {
        format("TSkuListWithVersionResult(success:true, status:TStatus(code:100000, msg:成功), data:TSkuListWithVersion(data:[TSkuDescendantsDTO(sku:TSkuDTO(id:141, name:pos收单产品-渠道版, spu:TSpuDTO(id:166, name:pos收单产品, leafCategory:TSimpleCategoryDTO(id:283, parentId:264, name:整机版本（服务+机具）, desc:, isLeaf:true, level:3), displayType:0), price:0, codeType:0, group:true, groupSelectType:2, members:[TSkuMemberDetailDTO(skuId:140, quantity:8, minQuantity:8, maxQuantity:8), TSkuMemberDetailDTO(skuId:135, minQuantity:0, maxQuantity:1), TSkuMemberDetailDTO(skuId:136, quantity:2, minQuantity:2, maxQuantity:2)], status:5, order:1, photo:TSkuPhotoDTO(id:103)), descendants:{133=TSkuDTO(id:133, name:移动_100M, spu:TSpuDTO(id:160, name:移动流量卡, leafCategory:TSimpleCategoryDTO(id:271, parentId:269, name:sim 卡, desc:, isLeaf:true, level:4), displayType:1), price:0, codeType:0, attributes:[TAttribute(nameId:248, valueId:344, name:商品制造商, value:移动, bizCode:GOODS_MFR, valueBizCode:9), TAttribute(nameId:251, valueId:335, name:流量套餐, value:100M, bizCode:SIM_DATA_PLAN, valueBizCode:100M), TAttribute(nameId:253, valueId:330, name:商品类型, value:SIM卡流量套餐, bizCode:GOODS_TYPE, valueBizCode:2)], group:false, groupSelectType:0, members:[], status:5, order:1, photo:TSkuPhotoDTO(id:95)), 134=TSkuDTO(id:134, name:移动_300M, spu:TSpuDTO(id:160, name:移动流量卡, leafCategory:TSimpleCategoryDTO(id:271, parentId:269, name:sim 卡, desc:, isLeaf:true, level:4), displayType:1), price:0, codeType:0, attributes:[TAttribute(nameId:248, valueId:344, name:商品制造商, value:移动, bizCode:GOODS_MFR, valueBizCode:9), TAttribute(nameId:251, valueId:336, name:流量套餐, value:300M, bizCode:SIM_DATA_PLAN, valueBizCode:300M), TAttribute(nameId:253, valueId:330, name:商品类型, value:SIM卡流量套餐, bizCode:GOODS_TYPE, valueBizCode:2)], group:false, groupSelectType:0, members:[], status:5, order:2, photo:TSkuPhotoDTO(id:96)), 135=TSkuDTO(id:135, name:100m流量包, spu:TSpuDTO(id:162, name:流量包, leafCategory:TSimpleCategoryDTO(id:286, parentId:264, name:服务套餐, desc:, isLeaf:true, level:3), displayType:0), price:0, codeType:0, attributes:[TAttribute(nameId:253, valueId:330, name:商品类型, value:SIM卡流量套餐, bizCode:GOODS_TYPE, valueBizCode:2)], group:true, groupSelectType:1, members:[TSkuMemberDetailDTO(skuId:133, quantity:1, minQuantity:1, maxQuantity:1), TSkuMemberDetailDTO(skuId:134, quantity:1, minQuantity:1, maxQuantity:1)], status:5, order:1, photo:TSkuPhotoDTO(id:97)), 136=TSkuDTO(id:136, name:pos支付产品, spu:TSpuDTO(id:163, name:支付产品, leafCategory:TSimpleCategoryDTO(id:285, parentId:264, name:支付产品, desc:, isLeaf:true, level:3), displayType:0), price:0, code:1, codeType:3, attributes:[TAttribute(nameId:253, valueId:332, name:商品类型, value:支付产品, bizCode:GOODS_TYPE, valueBizCode:41)], group:true, groupSelectType:2, members:[TSkuMemberDetailDTO(skuId:126, quantity:1, minQuantity:1, maxQuantity:1), TSkuMemberDetailDTO(skuId:127, quantity:1, minQuantity:1, maxQuantity:1)], status:5, order:1, photo:TSkuPhotoDTO(id:98)), 138=TSkuDTO(id:138, name:新大陆_N910_新机, spu:TSpuDTO(id:164, name:pos渠道版, leafCategory:TSimpleCategoryDTO(id:270, parentId:268, name:POS机, desc:, isLeaf:true, level:4), displayType:1), price:0, code:1000000610, codeType:1, attributes:[TAttribute(nameId:248, valueId:342, name:商品制造商, value:新大陆, bizCode:GOODS_MFR, valueBizCode:5), TAttribute(nameId:249, valueId:341, name:型号, value:N910, bizCode:DEVICE_MODE, valueBizCode:N910), TAttribute(nameId:250, valueId:337, name:设备等级, value:新机, bizCode:GOODS_GRADE, valueBizCode:1), TAttribute(nameId:253, valueId:329, name:商品类型, value:pos, bizCode:GOODS_TYPE, valueBizCode:1), TAttribute(nameId:252, valueId:333, name:是否启用SN, value:是, bizCode:HAS_SN, valueBizCode:YES)], group:false, groupSelectType:0, members:[], status:5, order:1, photo:TSkuPhotoDTO(id:100)), 139=TSkuDTO(id:139, name:新大陆_N910_旧机, spu:TSpuDTO(id:164, name:pos渠道版, leafCategory:TSimpleCategoryDTO(id:270, parentId:268, name:POS机, desc:, isLeaf:true, level:4), displayType:1), price:0, code:1000000641, codeType:1, attributes:[TAttribute(nameId:248, valueId:342, name:商品制造商, value:新大陆, bizCode:GOODS_MFR, valueBizCode:5), TAttribute(nameId:249, valueId:341, name:型号, value:N910, bizCode:DEVICE_MODE, valueBizCode:N910), TAttribute(nameId:250, valueId:338, name:设备等级, value:旧机, bizCode:GOODS_GRADE, valueBizCode:99), TAttribute(nameId:253, valueId:329, name:商品类型, value:pos, bizCode:GOODS_TYPE, valueBizCode:1), TAttribute(nameId:252, valueId:333, name:是否启用SN, value:是, bizCode:HAS_SN, valueBizCode:YES)], group:false, groupSelectType:0, members:[], status:5, order:2, photo:TSkuPhotoDTO(id:101)), 140=TSkuDTO(id:140, name:智能POS-渠道版, spu:TSpuDTO(id:165, name:智能POS-渠道版, leafCategory:TSimpleCategoryDTO(id:287, parentId:264, name:机具组合, desc:, isLeaf:true, level:3), displayType:0), price:0, codeType:0, attributes:[TAttribute(nameId:253, valueId:329, name:商品类型, value:pos, bizCode:GOODS_TYPE, valueBizCode:1)], group:true, groupSelectType:1, members:[TSkuMemberDetailDTO(skuId:138, quantity:1, minQuantity:1, maxQuantity:1), TSkuMemberDetailDTO(skuId:139, quantity:1, minQuantity:1, maxQuantity:1)], status:5, order:1, photo:TSkuPhotoDTO(id:102)), 126=TSkuDTO(id:126, name:微信支付, spu:TSpuDTO(id:159, name:支付服务, leafCategory:TSimpleCategoryDTO(id:274, parentId:266, name:支付服务, desc:, isLeaf:true, level:3), displayType:1), price:0, code:3, codeType:2, attributes:[TAttribute(nameId:253, valueId:331, name:商品类型, value:支付服务, bizCode:GOODS_TYPE, valueBizCode:21), TAttribute(nameId:254, valueId:345, name:支付服务, value:微信支付, bizCode:, valueBizCode:)], group:false, groupSelectType:0, members:[], status:5, order:1, photo:TSkuPhotoDTO(id:88)), 127=TSkuDTO(id:127, name:支付宝支付, spu:TSpuDTO(id:159, name:支付服务, leafCategory:TSimpleCategoryDTO(id:274, parentId:266, name:支付服务, desc:, isLeaf:true, level:3), displayType:1), price:0, code:4, codeType:2, attributes:[TAttribute(nameId:253, valueId:331, name:商品类型, value:支付服务, bizCode:GOODS_TYPE, valueBizCode:21), TAttribute(nameId:254, valueId:346, name:支付服务, value:支付宝支付, bizCode:, valueBizCode:)], group:false, groupSelectType:0, members:[], status:5, order:2, photo:TSkuPhotoDTO(id:89))})], version:3146))");
    }

    public static void format(String target) {
        char[] chars = target.toCharArray();
        int tabCount = 0;
        for (char c : chars) {
            switch (c) {
                case '(' :
                    tabCount++;
                    printTab(tabCount);
                    break;
                case ')' :
                    tabCount--;
                    break;
                case '[':
                    tabCount++;
                    printTab(tabCount);
                    break;
                case ']':
                    tabCount--;
                    break;
                case ',':
                    printTab(tabCount);
                    break;
                default:
                    if (c != ' ') {
                        System.out.print(c);
                    }
            }
        }
        System.out.println();
    }

    private static void printTab(int count) {
        System.out.print("\n");
        for (int i = 0; i < count; i++) {
            System.out.print("|\t");
        }
    }

}

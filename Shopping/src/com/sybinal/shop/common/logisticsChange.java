package com.sybinal.shop.common;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import com.sybinal.shop.model.countryCode;
import com.sybinal.shop.model.logOutOf;

public class logisticsChange {
	
	public static String pd(String data,List<countryCode>codes) {
		String[]datas=new String[1];
		datas[0]="未找到该国家";
		codes.forEach(x->{
			if(x.getShortCode().equals(data)) {
				datas[0]=x.getcName()+","+x.geteName();
			}
		});
		/*switch (data) {
		case "US":
			return "美国,America";

		case "CA":
			return "加拿大,Canada";

		case "JP":
			return "日本,Japan";

		case "AE":
			return "阿拉伯联合酋长国,TheUnitedArabEmirates";

		case "FR":
			return "法国,France";
		case "IT":
			return "意大利,Italy";

		case "ES":
			return "西班牙,Spain";

		case "UK":
			return "英国,Britain";

		case "DE":
			return "德国,Germany";
		case "CH":
			return "瑞士,Switzerland";
		case "GB":
			return "英国,Britain";
		case "GP":
			return "瓜德罗普,Guadeloupe";
		case "SA":
			return "沙特阿拉伯,Saudi Arabia";
		case "NO":
			return "挪威,Norway";
		case "SE":
			return "瑞典,Sweden";
		case "UA":
			return "乌克兰,Ukraine";
		case "IL":
			return "以色列,Israel";
		case "PT":
			return "葡萄牙,Portugal";
		case "BR":
			return "巴西,Brazil";
		case "AU":
			return "澳大利亚,Australia";
		case "IE":
			return "爱尔兰,Ireland";
		case "BE":
			return "比利时,Belgium";
		case "FI":
			return "芬兰,Finland";
		case "PL":
			return "波兰,poland";
		case "MX":
			return "墨西哥,Mexico";
		case "HK":
			return "香港,Hongkong";
		case "KR":
			return "韩国,Korea";
		case "TW":
			return "台湾,Taiwan";
		case "SK":
			return "斯洛伐克,Slovakia";
		case "PH":
			return "菲律宾,Philippines";
		case "TH":
			return "泰国,Thailand";
		}*/
		return datas[0];
	}

	public static String randomNum(int c) {
		String result = "";
		String result2 = "";
		for (int i = 1; i <= c; i++) // 6次执行输出6个不同字符
		{
			// 判断产生的随机数是0还是1，是0进入if语句用于输出数字，是1进入else用于输出字符
			int rd = Math.random() >= 0.5 ? 1 : 0;

			if (rd == 0) {
				Random r = new Random();
				result = r.nextInt(9) + 1 + ""; // 产生1-9数字

			} else {
				// 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				char[] A_z = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
						's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
				Random sc = new Random();
				int sub = sc.nextInt(A_z.length);
				result = A_z[sub] + ""; // 产生A——z字符

			}
			result2 += result;
		}
		return result2;

	}

	public static String pds(String data, List<logOutOf> list) {

		String[]datas=new String[1];
		datas[0]="未找到该物流";
		list.forEach(x->{
			if(x.getShortName().equals(data)) {
				datas[0]=x.getLogisticsName();
			}
		});
		/*switch (data) {
		case "cm_dd_sz":
			return "线下平邮(带电)";
		case "cm_sx_sz":
			return "线下平邮(普货)";
		case "ghxb_dd_sz":
			return "线下挂号(带电)";
		case "ghxb_sg_sz":
			return "线下挂号(普货)";
		case "cm_eub_szyjdd":
			return "线下E邮宝(带电)";
		case "cm_eub_szyj":
			return "线下E邮宝(普货)";
		case "cm_rb_shhm":
			return "国际专线/日本专线";
		case "cm_j_wbsd":
			return "欧洲经济专线挂号";
		case "cm_py_wbsd":
			return "欧洲经济专线平邮";
		case "cm_dg_shhm":
			return "德国专线";
		case "cm_yg_wbsd":
			return "英国专线";
		case "cm_spain_wbsd":
			return "西班牙专线";
		case "cm_it_hh":
			return "意大利专线平邮";
		case "cm_jna_ltzx":
			return "加拿大专线";
		case "xn_fg_wbsd":
			return "法国专线";
		case "cm_hm_yg":
			return "上海黑猫英国Hermes48经济专线";
		case "cm_jy_jnd":
			return "加拿大专线2";
		case "cm_jnd_jy":
			return "加拿大带电专线";
		case "xn_fg_hm":
			return "法国带电专线";
		case "cm_esdd_hh":
			return "西班牙带电专线";
		case "cm_it_ft":
			return "意大利专线";
		case "cm_ydl_ft":
			return "意大利专线平邮2";
		case "xn_dg_hm":
			return "德国带电专线";
		case "cm_dgdd_bx":
			return "德国带电专线挂号";
		}*/
		return datas[0];
	}

	// 邮政
	public static String pd2(String data) {
		if(data.equals("cm_dd_sz")||
		  data.equals("cm_sx_sz")||
		  data.equals("ghxb_dd_sz")||
		  data.equals("ghxb_sg_sz")) {
			return "中国邮政";
		}else if(data.equals("cm_eub_szyjdd")||
				 data.equals("cm_eub_szyj")){
			return "E邮宝";
		}else {
			return "其他";
		}
			
			
		/*switch (data) {
		case "cm_dd_sz":
			return "中国邮政";
		case "cm_sx_sz":
			return "中国邮政";
		case "ghxb_dd_sz":
			return "中国邮政";
		case "ghxb_sg_sz":
			return "中国邮政";
		case "cm_eub_szyjdd":
			return "E邮宝";
		case "cm_eub_szyj":
			return "E邮宝";
		case "cm_rb_shhm":
			return "其他";
		case "cm_j_wbsd":
			return "其他";
		case "cm_py_wbsd":
			return "其他";
		case "cm_dg_shhm":
			return "其他";
		case "cm_yg_wbsd":
			return "其他";
		case "cm_spain_wbsd":
			return "其他";
		case "cm_it_hh":
			return "其他";
		case "cm_jna_ltzx":
			return "其他";
		case "xn_fg_wbsd":
			return "其他";
		case "cm_hm_yg":
			return "其他";
		case "cm_jy_jnd":
			return "其他";
		case "cm_jnd_jy":
			return "其他";
		case "xn_fg_hm":
			return "其他";
		case "cm_esdd_hh":
			return "其他";
		case "cm_it_ft":
			return "其他";
		case "cm_ydl_ft":
			return "其他";
		case "cm_dgdd_bx":
			return "其他";
		}
		return null;*/
	}

	public static String gj(String data,List<countryCode>codes) {
		String[]datas=new String[1];
		datas[0]="未找到该国家";
		codes.forEach(x->{
			if(x.getShortCode().equals(data)) {
				datas[0]=x.getcName();
			}
		}); 
		/*switch (data) {
		case "US":
			return "美国";

		case "CA":
			return "加拿大";

		case "JP":
			return "日本";

		case "AE":
			return "阿拉伯联合酋长国";

		case "FR":
			return "法国";

		case "IT":
			return "意大利";

		case "ES":
			return "西班牙";

		case "UK":
			return "英国";

		case "DE":
			return "德国";
		case "CH":
			return "瑞士";
		case "GB":
			return "英国";
		case "GP":
			return "瓜德罗普";
		case "SA":
			return "沙特阿拉伯";
		case "NO":
			return "挪威";
		case "SE":
			return "瑞典";
		case "UA":
			return "乌克兰";
		case "IL":
			return "以色列";
		case "PT":
			return "葡萄牙";
		case "BR":
			return "巴西";
		case "AU":
			return "澳大利亚";
		case "IE":
			return "爱尔兰";
		case "BE":
			return "比利时";
		case "FI":
			return "芬兰";
		case "PL":
			return "波兰";
		case "MX":
			return "墨西哥";
		case "HK":
			return "香港";
		case "KR":
			return "韩国";
		case "TW":
			return "台湾";
		case "SK":
			return "斯洛伐克";
		case "PH":
			return "菲律宾";
		case "TH":
			return "泰国";
		}*/
		return datas[0];
	}

	// 千克转克
	public String kgG(String data) {

		return getDoubleString(Double.parseDouble(data) * 1000);
	}

	/*
	 * 如果是小数，保留两位，非小数，保留整数
	 * 
	 * @param number
	 */
	public static String getDoubleString(double number) {
		String numberStr;
		if (((int) number * 1000) == (int) (number * 1000)) {
			// 如果是一个整数
			numberStr = String.valueOf((int) number);
		} else {
			DecimalFormat df = new DecimalFormat("######0.00");
			numberStr = df.format(number);
		}
		return numberStr;
	}
}

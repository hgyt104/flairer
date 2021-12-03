package stu.inflp.flairer.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stu.inflp.flairer.vo.ProductVO;

public class CookieController {

	private static final int MAX_VIEW_COUNT = 5;

	private HttpServletResponse response;
	private Cookie[] cookies;
	private String[] keyList = { "MAXV", "pcode", "purl", "pname", "price" };

	public CookieController(HttpServletRequest request, HttpServletResponse response) {

		this.response = response;
		this.cookies = request.getCookies();
	}

	public void addCookies(ProductVO pfpv) throws Exception {

		int maxv = 0;
		int index = 0;
		int check = -1;
		boolean guardCookie;
		boolean isMAX = false;
		boolean isDuple = false;

		String keyName = "";
		String tempValue = "";
		Cookie tempCookie;

		String pcode = pfpv.getPcode();
		String purl = pfpv.getPictureurl();
		String pname = pfpv.getName();
		String price = Integer.toString(pfpv.getPrice());
		if (cookies != null) {

			for (Cookie c : cookies) {
				if (c.getName().equals("MAXV")) {
					maxv = Integer.parseInt(c.getValue());
				}
			}
		}
		boolean isEmpty = (maxv == 0);
		if (!isEmpty) {
			isMAX = (maxv == MAX_VIEW_COUNT);
			// isDuple = isDuplePcode(pcode);
			check = findDuplePcode(pcode);
			isDuple = check > -1;
		}

		for (int i = 0; i < (isEmpty ? keyList.length : cookies.length); i++) {

			guardCookie = true;
			if (!isEmpty) {
				keyName = cookies[i].getName();
				tempValue = cookies[i].getValue();
			}

			switch (isEmpty ? keyList[i] : keyName) {
				case "MAXV":
					tempValue = isEmpty ? "1"
							: ((isMAX || isDuple) ? tempValue : (Integer.toString(Integer.parseInt(tempValue) + 1)));
					break;
				case "pcode":
				case "price":
					if (isEmpty) {
						tempValue = keyList[i].equals("pcode") ? pcode : price;
					} else {
						String temp = keyName.equals("pcode") ? pcode : price;
						tempValue = createValue(tempValue, temp, check);
						if (isMAX && !isDuple) {
							index = tempValue.indexOf("|");
							tempValue = tempValue.substring(index + 1);
						}
					}
					break;
				case "purl":
				case "pname":
					String temp = isEmpty ? keyList[i] : keyName;
					temp = temp.equals("purl") ? purl : pname;
					if (isEmpty) {
						temp = replaceValue(temp, true);
						tempValue = encode(temp);
					} else {
						tempValue = decode(tempValue);
						tempValue = replaceValue(tempValue, false);
						tempValue = createValue(tempValue, temp, check);

						if (isMAX && !isDuple) {
							index = tempValue.indexOf("|");
							tempValue = tempValue.substring(index + 1);
						}

						tempValue = replaceValue(tempValue, true);
						tempValue = encode(tempValue);
					}
					break;
				default:
					guardCookie = false;
					break;
			}
			if (guardCookie) {
				if (!isEmpty) {
					cookies[i].setMaxAge(0);
				}
				tempCookie = new Cookie((isEmpty ? keyList[i] : keyName), tempValue);
				tempCookie.setPath("/");
				response.addCookie(tempCookie);
			}
		}
	}

	protected boolean isDuplePcode(String value) {

		for (Cookie c : cookies) {
			if (c.getName().equals("pcode")) {
				String[] temp = c.getValue().split("\\|");
				for (String s : temp) {
					if (value.equals(s)) {
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}

	protected int findDuplePcode(String value) {

		for (Cookie c : cookies) {
			if (c.getName().equals("pcode")) {
				String[] temp = c.getValue().split("\\|");
				for (int i = 0; i < temp.length; i++) {
					if (value.equals(temp[i])) {
						return i;
					}
				}
				return -1;
			}
		}
		return -1;
	}

	protected String createValue(String origin, String newValue, int fdp) {

		String[] arr = origin.split("\\|");
		String result = "";

		for (int i = 0; i < arr.length; i++) {
			if (i != fdp) {
				result += "|" + arr[i];
			}
			/*
			 * if(!arr[i].equals(newValue)) {
			 * 
			 * result += "|" + arr[i]; }
			 */

		}

		result += "|" + newValue;

		return result.substring(1);
	}

	protected String replaceValue(String value, boolean isEncode) {

		char oldC = '/';
		char newC = '!';

		if (!isEncode) {
			oldC = '!';
			newC = '/';
		}
		value = value.replace(oldC, newC);

		if (isEncode) {
			oldC = ' ';
			newC = '~';
		} else {
			oldC = '~';
			newC = ' ';
		}

		return value.replace(oldC, newC);

	}

	public void deleteCookies() {

		for (Cookie c : cookies) {
			for (int i = 0; i < keyList.length; i++) {
				if (c.getName().equals(keyList[i])) {
					Cookie cookie = new Cookie(keyList[i], null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}

	public List<ProductVO> createRecent() throws Exception {
		List<ProductVO> list = new ArrayList<ProductVO>();
		int maxv = 0;
		String pcode = "";
		String purl = "";
		String pname = "";
		String price = "";

		for (Cookie c : cookies) {
			switch (c.getName()) {
				case "MAXV":
					maxv = Integer.parseInt(c.getValue());
					break;
				case "pcode":
					pcode = c.getValue();
					break;
				case "purl":
					purl = replaceValue(decode(c.getValue()), false);
					break;
				case "pname":
					pname = replaceValue(decode(c.getValue()), false);
					break;
				case "price":
					price = c.getValue();
					break;
				default:
					break;
			}
		}

		String[] pcodes = pcode.split("\\|");
		String[] purls = purl.split("\\|");
		String[] pnames = pname.split("\\|");
		String[] prices = price.split("\\|");

		for (int i = maxv - 1; i >= 0; i--) {
			ProductVO pfpv = new ProductVO();
			pfpv.setPcode(pcodes[i]);
			pfpv.setPictureurl(purls[i]);
			pfpv.setName(pnames[i]);
			pfpv.setPrice(Integer.parseInt(prices[i]));
			list.add(pfpv);
		}

		return list;
	}

	protected String encode(String target) throws Exception {
		return URLEncoder.encode(target, "UTF-8");
	}

	protected String decode(String target) throws Exception {
		return URLDecoder.decode(target, "UTF-8");
	}
}

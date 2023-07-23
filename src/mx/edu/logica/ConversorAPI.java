package mx.edu.logica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class ConversorAPI {

	private String apiKeyURL = "apikey=fca_live_qRE0NUZI3AQbiEIHwjaEUedqUllx2Xuk0wgw8mKU";
	private String url = "https://api.freecurrencyapi.com/v1/latest?";
	private String currencies = "&currencies=EUR%2CUSD%2CJPY%2CKRW%2CRUB%2CMXN%2CGBP&base_currency=";
	private BigDecimal euro;
	private BigDecimal usDolar;
	private BigDecimal yenJapan;
	private BigDecimal wonKorea;
	private BigDecimal russianRuble;
	private BigDecimal mexPeso;
	private BigDecimal poundBritish;
	private BigDecimal result;

	public ConversorAPI(String baseCurrency) {
		try {

			URL url = new URL(this.url + this.apiKeyURL + this.currencies + baseCurrency);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Ocurrió un error: " + responseCode);
			} else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}

				scanner.close();

				JSONObject jsonObj = new JSONObject(informationString.toString());
				JSONObject jsonFinal = new JSONObject(jsonObj.getJSONObject("data").toString());
				this.euro = new BigDecimal(jsonFinal.toMap().get("EUR").toString());
				this.usDolar = new BigDecimal(jsonFinal.toMap().get("USD").toString());
				this.yenJapan = new BigDecimal(jsonFinal.toMap().get("JPY").toString());
				this.wonKorea = new BigDecimal(jsonFinal.toMap().get("KRW").toString());
				this.russianRuble = new BigDecimal(jsonFinal.toMap().get("RUB").toString());
				this.mexPeso = new BigDecimal(jsonFinal.toMap().get("MXN").toString());
				this.poundBritish = new BigDecimal(jsonFinal.toMap().get("GBP").toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BigDecimal multiplication(BigDecimal currency, String country) {
		
		switch(country) {
		case "EUR":
			this.result = getEuro().multiply(currency);
			break;
		case "USD":
			this.result = getUsDolar().multiply(currency);
			break;
		case "JPY":
			this.result = getYenJapan().multiply(currency);
			break;
		case "KRW":
			this.result = getWonKorea().multiply(currency);
			break;
		case "RUB":
			this.result = getRussianRuble().multiply(currency);
			break;
		case "MXN":
			this.result = getMexPeso().multiply(currency);
			break;
		case "GBP":
			this.result = getPoundBritish().multiply(currency);
			break;
		}
		return this.result.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getEuro() {
		return euro.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getUsDolar() {
		return usDolar.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getYenJapan() {
		return yenJapan.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getWonKorea() {
		return wonKorea.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getRussianRuble() {
		return russianRuble.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getMexPeso() {
		return mexPeso.setScale(5, RoundingMode.HALF_UP);
	}

	public BigDecimal getPoundBritish() {
		return poundBritish.setScale(5, RoundingMode.HALF_UP);
	}
	
}

package restapi.automation.helperutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HelperUtils {
	
	public static Object[][] getDataFromJSON(final String jsonPath, final String jsonArrayName) {
		Map<String, String> datamap;
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = null;
		Object[][] data = null;
		if (jsonArrayName != null) {
			try {
				jsonObject = jsonParser.parse(new FileReader(new File(jsonPath))).getAsJsonObject();
				if (jsonObject != null) {
					JsonArray jsonArray = (JsonArray) jsonObject.get(jsonArrayName);
					data = new Object[jsonArray.size()][1];
					int rowNumber = 0;
					for (JsonElement jsonElemet : jsonArray) {
						datamap = new HashMap<>();
						for (Map.Entry<String, JsonElement> entry : jsonElemet.getAsJsonObject().entrySet()) {
							datamap.put(entry.getKey(), entry.getValue().toString().replaceAll("\"", ""));

						}
						data[rowNumber][0] = datamap;
						rowNumber++;
					}

				}
			} catch (FileNotFoundException e) {
				System.out.println("File Not found at the location :" + jsonPath + " Exception is :" + e);

			}
		}
		return data;

	}
}

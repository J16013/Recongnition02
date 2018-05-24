package recongnition02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recongnition02_main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		VisualRecognition service = new VisualRecognition("2018-03-19");
		service.setApiKey("c9492283a95fc45911de623c781297a751b85a00");

		InputStream imagesStream = null;
		try {
			imagesStream = new FileInputStream("img/fruitbowl.jpg");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .imagesFile(imagesStream)
		  .imagesFilename("img/fruitbowl.jpg")
		  .threshold((float) 0.6)
		  .owners(Arrays.asList("IBM"))
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);

		String s = String.valueOf(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(s);


		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String class_fruit = node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(0).get("class")
				.asText();
		double classes_score1=node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(0).get("score")
				.asDouble();
		String class_color1=node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(1).get("class")
				.asText();
		double classes_score2=node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(1).get("score")
				.asDouble();
		String class_color2=node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(2).get("class")
				.asText();
		double classes_score3=node.get("images")
				.get(0).get("classifiers")
				.get(0).get("classes")
				.get(2).get("score")
				.asDouble();

		System.out.println("class:"+class_fruit);
		System.out.println("score:"+classes_score1);
		System.out.println("class:"+class_color1);
		System.out.println("score:"+classes_score2);
		System.out.println("class:"+class_color2);
		System.out.println("score:"+classes_score3);
	}

}

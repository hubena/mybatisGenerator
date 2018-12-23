package generator;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 主类
 *
 * @author 曾谢波
 * @since 2018年11月3日
 */
public class GeneratorMain {
	public static void main(String[] args) {
		shell();
	}

	private static void shell() {
		List<String> warnings = new ArrayList<String>();
		try {
			boolean overwrite = true;
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp
					.parseConfiguration(GeneratorMain.class.getResourceAsStream("../generatorConfig.xml"));
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			ProgressCallback progressCallback = new VerboseProgressCallback();
			// myBatisGenerator.generate(null);
			myBatisGenerator.generate(progressCallback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}
}
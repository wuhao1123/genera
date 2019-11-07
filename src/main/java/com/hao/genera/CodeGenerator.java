package com.hao.genera;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hao.genera.utils.Func;
import com.hao.genera.utils.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.*;

/**
 * 代码生成器配置类
 *
 * @author 吴昊
 */
@Data
@Slf4j
public class CodeGenerator {
	/**
	 * 代码生成的包名
	 */
	private String packageName = "com.hao.genera";
	/**
	 * 代码后端生成的地址
	 */
	private String packageDir;
	/**
	 * 需要去掉的表前缀
	 */
	private String[] tablePrefix = {"test_"};
	/**
	 * 需要生成的表名(两者只能取其一)
	 */
	private String[] includeTables = {"test_user"};
	/**
	 * 需要排除的表名(两者只能取其一)
	 */
	private String[] excludeTables = {};
	/**
	 * 基础包名
	 */
	private String basePackage = "com.hao.genera.base.";
	/**
	 * 是否包含基础业务字段
	 */
	private Boolean hasSuperEntity = Boolean.TRUE;
	/**
	 * 是否包含包装器
	 */
	private Boolean hasWrapper = Boolean.FALSE;
	/**
	 * 基础业务字段
	 */
	private String[] superEntityColumns = {"created", "updated", "deleted"};
	/**
	 * 租户字段
	 */
	private String tenantColumn = "tenant_id";
	/**
	 * 是否启用swagger
	 */
	private Boolean isSwagger2 = Boolean.TRUE;
	/**
	 * 数据库驱动名
	 */
	private String driverName;
	/**
	 * 数据库链接地址
	 */
	private String url;
	/**
	 * 数据库用户名
	 */
	private String username;
	/**
	 * 数据库密码
	 */
	private String password;

	public void run() {
		Properties props = getProperties();
		AutoGenerator mpg = new AutoGenerator();
		GlobalConfig gc = new GlobalConfig();
		String outputDir = getOutputDir();
		String author = props.getProperty("author");
		gc.setOutputDir(outputDir);
		gc.setAuthor(author);
		gc.setFileOverride(true);
		gc.setOpen(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setSwagger2(isSwagger2);
		mpg.setGlobalConfig(gc);
		DataSourceConfig dsc = new DataSourceConfig();
		String driverName = Func.toStr(this.driverName, props.getProperty("spring.datasource.driver-class-name"));
		if (StringUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
			dsc.setDbType(DbType.MYSQL);
			dsc.setTypeConvert(new MySqlTypeConvert());
		} else if (StringUtil.containsAny(driverName, DbType.POSTGRE_SQL.getDb())) {
			dsc.setDbType(DbType.POSTGRE_SQL);
			dsc.setTypeConvert(new PostgreSqlTypeConvert());
		} else {
			dsc.setDbType(DbType.ORACLE);
			dsc.setTypeConvert(new OracleTypeConvert());
		}
		dsc.setDriverName(driverName);
		dsc.setUrl(Func.toStr(this.url, props.getProperty("spring.datasource.url")));
		dsc.setUsername(Func.toStr(this.username, props.getProperty("spring.datasource.username")));
		dsc.setPassword(Func.toStr(this.password, props.getProperty("spring.datasource.password")));
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setTablePrefix(tablePrefix);
		if (includeTables.length > 0) {
			strategy.setInclude(includeTables);
		}
		if (excludeTables.length > 0) {
			strategy.setExclude(excludeTables);
		}
		if (hasSuperEntity) {
			strategy.setSuperEntityClass(basePackage + "BaseEntity");
			strategy.setSuperEntityColumns(superEntityColumns);
			strategy.setSuperServiceClass(basePackage + "BaseService");
			strategy.setSuperServiceImplClass(basePackage + "BaseServiceImpl");
		}
		// 自定义 controller 父类
		strategy.setEntityBuilderModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		// 控制台扫描
		pc.setModuleName(null);
		pc.setParent(packageName);
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);
		mpg.setCfg(getInjectionConfig());
		mpg.execute();
	}

	private InjectionConfig getInjectionConfig() {
		// 自定义配置
		Map<String, Object> map = new HashMap<>(16);
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				map.put("tenantColumn", tenantColumn);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/sql/menu.sql.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				map.put("entityKey", (tableInfo.getEntityName().toLowerCase()));
				map.put("menuId", IdWorker.getId());
				map.put("addMenuId", IdWorker.getId());
				map.put("editMenuId", IdWorker.getId());
				map.put("removeMenuId", IdWorker.getId());
				map.put("viewMenuId", IdWorker.getId());
				return getOutputDir() +  "/sql/" + tableInfo.getEntityName().toLowerCase() + ".menu.mysql";
			}
		});
		focList.add(new FileOutConfig("/templates/entityVO.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "vo" + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/entityDTO.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "dto" + "/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		return cfg;
	}

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	private Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource("/templates/code.properties");
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 生成到项目中
	 *
	 * @return outputDir
	 */
	public String getOutputDir() {
		return (Func.isBlank(packageDir) ? System.getProperty("user.dir") : packageDir) + "/src/main/java";
	}

	public static void main(String[] args) {
		CodeGenerator bladeCodeGenerator = new CodeGenerator();
		bladeCodeGenerator.run();
	}
}

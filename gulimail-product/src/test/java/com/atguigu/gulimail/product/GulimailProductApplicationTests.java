package com.atguigu.gulimail.product;

import com.atguigu.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.gulimail.product.dao.AttrGroupDao;
import com.atguigu.gulimail.product.dao.SkuSaleAttrValueDao;
import com.atguigu.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimail.product.entity.AttrEntity;
import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.AttrService;
import com.atguigu.gulimail.product.service.BrandService;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.service.SkuInfoService;
import com.atguigu.gulimail.product.vo.skuItemvo.SkuItemSaleAttrsVo;
import com.atguigu.gulimail.product.vo.skuItemvo.SkuItemVo;
import com.atguigu.gulimail.product.vo.skuItemvo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
class GulimailProductApplicationTests {

//	@Autowired
//	OSSClient ossClient;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private AttrService attrService;

	@Autowired
	private AttrAttrgroupRelationDao relation;

	@Autowired
	private SkuInfoService skuInfoService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private AttrGroupDao attrGroupDao;

	@Autowired
	private SkuSaleAttrValueDao skuSaleAttrValueDao;

	@Test
	void testRedis(){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set("hello","world"+ UUID.randomUUID().toString());
		String s = ops.get("hello");
		System.out.println("???????????????:"+s);
	}

	@Test
	void contextLoads() {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setName("??????");
		brandService.save(brandEntity);
		System.out.println("????????????...");
	}

	@Test
	void text(){
		Long[] path = categoryService.findCatelogPath(302L);
		//System.out.println(path);
		log.info("????????????:{}", Arrays.asList(path));
	}

	@Test
	void text2(){
		List<AttrEntity> list = attrService.getRelationAttr(100L);
		System.out.println(list);
	}

	@Test
	void text3(){
		List<AttrAttrgroupRelationEntity> entities = relation.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", 12L));
		System.out.println(entities);
	}

	@Test
	void test4(){
		List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(100L, 225L);
		System.out.println(attrGroupWithAttrsBySpuId);
	}

	@Test
	void test5(){
		List<SkuItemSaleAttrsVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(5L);
		System.out.println(saleAttrsBySpuId);
	}
	@Test
	void test6() throws ExecutionException, InterruptedException {
		SkuItemVo item = skuInfoService.item(5L);
		System.out.println(item);
	}
//	@Test
//	void testUpload()throws FileNotFoundException{
//		InputStream inputStream = new FileInputStream("D:\\picture\\03.jpg");
//		ossClient.putObject("gulimall-adverseq","test03.jpg",inputStream);
//	}
//
//	@Test
//	void testUpload02() throws FileNotFoundException{
////		// Endpoint????????????????????????Region???????????????????????????
////		String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
////// ?????????AccessKey?????????API????????????????????????????????????????????????????????????????????????RAM???????????????API????????????????????????????????? https://ram.console.aliyun.com ?????????
////		String accessKeyId = "<yourAccessKeyId>";
////		String accessKeySecret = "<yourAccessKeySecret>";
//
//// ??????OSSClient?????????
////		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//// ??????????????????
//		InputStream inputStream = new FileInputStream("D:\\picture\\02n.jpg");
//		ossClient.putObject("gulimall-adverseq", "02n.jpg", inputStream);
//
//// ??????OSSClient???
//		ossClient.shutdown();
//	}
}

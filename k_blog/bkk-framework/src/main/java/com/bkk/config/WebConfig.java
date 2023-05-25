package com.bkk.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bkk.common.JacksonObjectMapper;
import com.bkk.config.intercept.AccessLimitIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 这里需要先将限流拦截器入住，不然无法获取到拦截器中的redisCache
     * @return
     */
    @Bean
    public AccessLimitIntercept getAccessLimitIntercept() {
        return new AccessLimitIntercept();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //IP限流拦截器
        registry.addInterceptor(getAccessLimitIntercept()).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

//    @Bean//使用@Bean注入fastJsonHttpMessageConvert
//    public HttpMessageConverter fastJsonHttpMessageConverters() {
//        //1.需要定义一个Convert转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        SerializeConfig.globalInstance.put(Long.class, ToStringSerializer.instance);
//
//        fastJsonConfig.setSerializeConfig(SerializeConfig.globalInstance);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fastConverter;
//        return converter;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(fastJsonHttpMessageConverters());
//    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, mappingJackson2HttpMessageConverter);
    }


}
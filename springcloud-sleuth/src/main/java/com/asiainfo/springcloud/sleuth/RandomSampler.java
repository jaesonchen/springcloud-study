package com.asiainfo.springcloud.sleuth;

import java.util.Random;

import brave.sampler.Sampler;

/**   
 * @Description: zipkin采样率
 * 
 * @author chenzq  
 * @date 2019年5月23日 下午6:17:55
 * @version V1.0
 * @Copyright: Copyright(c) 2019 jaesonchen.com Inc. All rights reserved. 
 */
public class RandomSampler extends Sampler {

    Random random = new Random();
    
    @Override
    public boolean isSampled(long traceId) {
        return random.nextInt(100) < 10;
    }
}

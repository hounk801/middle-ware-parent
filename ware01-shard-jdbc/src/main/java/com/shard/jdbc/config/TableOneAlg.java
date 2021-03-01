package com.shard.jdbc.config;

import com.shard.jdbc.utils.HashUtil;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;

/**
 * 分表算法
 */
@Slf4j
public class TableOneAlg implements PreciseShardingAlgorithm<String> {

    /**
     * 该表每个库分5张表
     */
    @Override
    public String doSharding(Collection<String> names, PreciseShardingValue<String> value) {
        log.info("分表算法参数 {},{}",names,value);
        int hash = HashUtil.rsHash(String.valueOf(value.getValue()));
        return "table_one_" + (hash % 5+1);
    }
}

package org.redisson.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.cache.CacheException;
import org.redisson.hibernate.RedissonRegionFactory;
import org.slf4j.Logger;

public class ConfigPrinter {
    
    public void print(Logger log, String configPath) {
		Config config = loadRedissonConfig(configPath);
        if (config.isClusterConfig()) {
            ClusterServersConfig clusterServersConfig = config.getClusterServersConfig();
            log.info("Client name: {}", clusterServersConfig.getClientName());
            log.info("Nodes: {}", clusterServersConfig.getNodeAddresses());
            log.info("Username: {}", clusterServersConfig.getUsername());
            String password = clusterServersConfig.getPassword();
            password = password.substring(0, 1) + StringUtils.repeat("*", password.length()-2) + password.substring(password.length()-1);
    
            log.info("Password: {}", clusterServersConfig.getPassword());
            log.info("Connection timeout: {}", clusterServersConfig.getConnectTimeout());
            log.info("Timeout: {}", clusterServersConfig.getTimeout());
            log.info("Master connection pool size: {}", clusterServersConfig.getMasterConnectionPoolSize());
            log.info("Slave connection pool size: {}", clusterServersConfig.getSlaveConnectionPoolSize());
            log.info("Retry attempts: {}", clusterServersConfig.getRetryAttempts());
        } else if (config.getSingleServerConfig() != null) {
            SingleServerConfig singleServerConfig = config.getSingleServerConfig();
            log.info("Client name: {}", singleServerConfig.getClientName());
            log.info("Address: {}", singleServerConfig.getAddress());
            log.info("Username: {}", singleServerConfig.getUsername());
            log.info("Password: {}", singleServerConfig.getPassword());
            log.info("Connection timeout: {}", singleServerConfig.getConnectTimeout());
            log.info("Connection pool size: {}", singleServerConfig.getConnectionPoolSize());
            log.info("Timeout: {}", singleServerConfig.getTimeout());
            log.info("Retry attempts: {}", singleServerConfig.getRetryAttempts());
        }
    }

    protected Config loadRedissonConfig(String configPath) {
        Config config = null;
        if (StringUtils.isBlank(configPath)) {
            config = loadConfig(RedissonRegionFactory.class.getClassLoader(), "redisson.json");
            if (config == null) {
                config = loadConfig(RedissonRegionFactory.class.getClassLoader(), "redisson.yaml");
            }
        } else {
            config = loadConfig(RedissonRegionFactory.class.getClassLoader(), configPath);
            if (config == null) {
                config = loadConfig(configPath);
            }
        }
        if (config == null) {
            throw new CacheException("Unable to locate Redisson configuration");
        }
        return config;
    }

	private Config loadConfig(String configPath) {
        try {
            return Config.fromYAML(new File(configPath));
        } catch (IOException e) {
            // trying next format
            try {
                return Config.fromJSON(new File(configPath));
            } catch (IOException e1) {
                throw new CacheException("Can't parse default yaml config", e1);
            }
        }
    }

	private Config loadConfig(ClassLoader classLoader, String fileName) {
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is != null) {
            try {
                return Config.fromYAML(is);
            } catch (IOException e) {
                try {
                    is = classLoader.getResourceAsStream(fileName);
                    return Config.fromJSON(is);
                } catch (IOException e1) {
                    throw new CacheException("Can't parse yaml config", e1);
                }
            }
        }
        return null;
    }

}

package scanner;

import definition.PoolResource;

/**
 * description:
 *
 * @author zhun.huang 2019-03-20
 */
public interface ResourceScanner {

    /**
     * 对poolResource进行 扫描，将扫描后的数据存储在poolResourceStats上
     * @param poolResource
     */
    void doScan(PoolResource poolResource);
}

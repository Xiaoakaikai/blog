package com.bkk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.constants.SqlConf;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Dict;
import com.bkk.domain.entity.DictData;
import com.bkk.mapper.DictDataMapper;
import com.bkk.service.DictDataService;
import com.bkk.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bkk.constants.SqlConf.DICT_TYPE_ID;
import static com.bkk.constants.SystemConstants.*;

/**
 * (DictData)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 20:43:15
 */
@Service("dictDataService")
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

    @Autowired
    private DictService dictService;

    /**
     * 根据字典类型获取字典数据
     * @param types
     * @return
     */
    @Override
    public ResponseResult getDataByDictType(List<String> types) {
        Map<String, Map<String, Object>> map = new HashMap<>();

        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dict::getType, types).eq(Dict::getIsPublish, DICT_STATUS_NORMAL);

        List<Dict> dictList = dictService.list(queryWrapper);
        dictList.forEach(item ->{
            LambdaQueryWrapper<DictData> dictDataQueryWrapper = new LambdaQueryWrapper<>();
            dictDataQueryWrapper.eq(DictData::getIsPublish, DICT_DATA_STATUS_NORMAL)
                            .eq(DictData::getDictId, item.getId())
                                    .orderByDesc(DictData::getSort);
            List<DictData> dataList = baseMapper.selectList(dictDataQueryWrapper);
            String defaultValue = null;
            for (DictData dictData : dataList) {
                //选取默认值
                if (dictData.getIsDefault().equals(ONE)){
                    defaultValue = dictData.getValue();
                    break;
                }
            }
            Map<String, Object> result = new HashMap<>();
            result.put(DEFAULT_VALUE,defaultValue);
            result.put(LIST,dataList);
            map.put(item.getType(),result);
        });
        return ResponseResult.okResult(map);
    }
}


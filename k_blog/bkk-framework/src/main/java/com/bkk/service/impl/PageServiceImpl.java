package com.bkk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkk.domain.ResponseResult;
import com.bkk.domain.entity.Page;
import com.bkk.mapper.PageMapper;
import com.bkk.service.PageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Page)表服务实现类
 *
 * @author makejava
 * @since 2023-03-30 16:45:40
 */
@Service("pageService")
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements PageService {

    /**
     * 页面列表
     * @return
     */
    @Override
    public ResponseResult PageList() {
        List<Page> pages = baseMapper.selectList(null);
        return ResponseResult.okResult(pages);
    }

    /**
     * 新增页面
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult add(Page page) {
        int insert = baseMapper.insert(page);
        return insert > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("添加失败！");
    }

    /**
     * 修改页面信息
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Page page) {
        int update = baseMapper.updateById(page);
        return update > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("修改失败！");
    }

    /**
     * 删除页面
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delete(Long id) {
        int delete = baseMapper.deleteById(id);
        return delete > 0 ? ResponseResult.okResult() : ResponseResult.errorResult("删除失败！");
    }


}


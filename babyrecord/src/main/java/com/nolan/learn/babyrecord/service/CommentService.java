package com.nolan.learn.babyrecord.service;

import com.nolan.learn.babyrecord.dao.CommentInfoRepository;
import com.nolan.learn.babyrecord.model.po.CommentInfoPO;
import com.nolan.learn.babyrecord.model.vo.CommentInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Service
public class CommentService {

    @Resource
    private CommentInfoRepository commentInfoRepository;

    public boolean add(CommentInfoVO commentInfoVO) {
        CommentInfoPO commentInfoPO = new CommentInfoPO();
        BeanUtils.copyProperties(commentInfoVO, commentInfoPO);
        commentInfoRepository.save(commentInfoPO);
        return true;
    }

    public boolean delete(Long id) {
        commentInfoRepository.deleteById(id);
        return true;
    }
}

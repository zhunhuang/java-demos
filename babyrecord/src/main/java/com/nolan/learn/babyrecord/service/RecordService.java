package com.nolan.learn.babyrecord.service;

import com.google.common.base.Strings;
import com.nolan.learn.babyrecord.dao.CommentInfoRepository;
import com.nolan.learn.babyrecord.dao.RecordInfoRepository;
import com.nolan.learn.babyrecord.dao.UserBabyRelationRepository;
import com.nolan.learn.babyrecord.exceptions.BusinessException;
import com.nolan.learn.babyrecord.model.po.CommentInfoPO;
import com.nolan.learn.babyrecord.model.po.RecordInfoPO;
import com.nolan.learn.babyrecord.model.po.UserBabyRelationPO;
import com.nolan.learn.babyrecord.model.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author zhun.huang 2019-06-07
 */
@Service
public class RecordService {

    @Resource
    private RecordInfoRepository recordInfoRepository;
    @Resource
    private CommentInfoRepository commentInfoRepository;
    @Resource
    private UserBabyRelationRepository userBabyRelationRepository;


    public List<RecordInfoExtendVO> query(RecordInfoRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.Direction.DESC, "gmtCreate");
        Page<RecordInfoPO> allByBabyID = recordInfoRepository.findByBabyId(request.getBabyId(), pageable);
        return recordPoListToVOList(allByBabyID.stream().collect(Collectors.toList()));
    }

    public boolean add(AddRecordRequest request) {
        checkAuthorize(request);
        RecordInfoPO recordInfoPO = AddRecordRequest.requestToPO(request);
        recordInfoRepository.save(recordInfoPO);
        return true;
    }

    public boolean delete(String userId, Long id) {
        Optional<RecordInfoPO> byId = recordInfoRepository.findById(id);
        if (!byId.isPresent() || !byId.get().getUserId().equals(userId)) {
            throw new BusinessException(-1,"没权限");
        }
        recordInfoRepository.deleteById(id);
        return true;
    }

    private void checkAuthorize(AddRecordRequest request) {
        UserBabyRelationPO byBabyIdAndUserId = userBabyRelationRepository.findByBabyIdAndUserId(request.getBabyId(), request.getUserId());
        if (byBabyIdAndUserId== null || byBabyIdAndUserId.getPrivilegeLevel()>2) {
            throw new BusinessException(-1,"权限不足");
        }
    }

    private List<RecordInfoExtendVO> recordPoListToVOList(List<RecordInfoPO> recordInfoPOS) {
        return recordInfoPOS.stream()
                .map(recordInfoPO -> {
                            List<CommentInfoPO> commentInfoPOList = commentInfoRepository.findByRecordId(recordInfoPO.getId());
                            List<CommentInfoVO> commentInfoVOList = commentInfoPOList.stream()
                                    .map(CommentInfoVO::poToVO).collect(Collectors.toList());

                            return RecordInfoExtendVO.poToVO(recordInfoPO, commentInfoVOList);
                        }
                ).collect(Collectors.toList());

    }

}

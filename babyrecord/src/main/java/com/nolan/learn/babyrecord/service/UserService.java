package com.nolan.learn.babyrecord.service;

import com.nolan.learn.babyrecord.dao.BabyInfoRepository;
import com.nolan.learn.babyrecord.dao.UserBabyRelationRepository;
import com.nolan.learn.babyrecord.model.po.BabyInfoPO;
import com.nolan.learn.babyrecord.model.po.UserBabyRelationPO;
import com.nolan.learn.babyrecord.model.vo.AddBabyRequest;
import com.nolan.learn.babyrecord.model.vo.BabyInfoVO;
import com.nolan.learn.babyrecord.model.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Resource
    private BabyInfoRepository babyInfoRepository;
    @Resource
    private UserBabyRelationRepository userBabyRelationRepository;


    public UserInfo homePage(String userId) {
        Optional<UserBabyRelationPO> userBabyRelationPO = userBabyRelationRepository.findFirstByUserId(userId);

        BabyInfoVO babyInfoVO = null;
        if (userBabyRelationPO.isPresent()) {
            BabyInfoPO babyInfoPO = babyInfoRepository.findByBabyId(userBabyRelationPO.get().getBabyId());
            if (babyInfoPO != null) {
                babyInfoVO = BabyInfoVO.poToVO(babyInfoPO, userBabyRelationPO.get().getPrivilegeLevel());
            }
        }
        return UserInfo.builder()
                .nickName("Mock用户名")
                .babyInfoVO(babyInfoVO)
                .userId(userId)
                .build();
    }

    public boolean addBaby(AddBabyRequest request) {
        BabyInfoPO babyInfoPO = AddBabyRequest.requestToPO(request);
        babyInfoRepository.save(babyInfoPO);
        return true;

    }
}

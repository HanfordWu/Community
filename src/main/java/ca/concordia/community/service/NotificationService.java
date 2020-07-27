package ca.concordia.community.service;

import ca.concordia.community.dto.NotificationDto;
import ca.concordia.community.dto.PaginationDto;
import ca.concordia.community.enums.NotificationTypeEnum;
import ca.concordia.community.mapper.NotificationMapper;
import ca.concordia.community.mapper.UserMapper;
import ca.concordia.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Hanford Wu on 2020-07-26 11:26 a.m.
 */
@Service
public class NotificationService {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDto<NotificationDto> list(Integer id,
                              Integer page,
                              Integer size) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        paginationDto.setPagination(totalCount, page, size);
        Integer offset = size * (page -1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notificationList.size() == 0){
            return paginationDto;
        }
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setType(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }

        paginationDto.setData(notificationDtos);
        return paginationDto;



    }

    public Long unreadCount(Integer id) {


        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        return notificationMapper.countByExample(example);

    }
}

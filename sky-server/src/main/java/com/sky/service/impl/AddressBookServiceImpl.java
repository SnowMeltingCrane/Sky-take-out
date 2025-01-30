package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.beans.beancontext.BeanContext;
import java.util.List;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     */
    @Override
    public void save(AddressBook addressBook) {
        log.info("新增地址：{}", addressBook);
        addressBook.setIsDefault(0);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.insert(addressBook);
    }

    /**
     * 条件查询地址
     * @param addressBook
     * @return
     */
    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        return addressBookMapper.list(addressBook);
    }


    /**
     * 根据id修改地址
     * @param addressBook
     */
    @Override
    public void update(AddressBook addressBook) {
        log.info("修改地址：{}", addressBook);
        addressBookMapper.update(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook
     */
    @Override
    @Transactional
    public void setDefault(AddressBook addressBook) {
        // 1、将当前用户的所有地址设置非默认地址
        Long userId = BaseContext.getCurrentId();
        addressBook.setUserId(userId);
        addressBook.setIsDefault(0);
        addressBookMapper.updateIsDefaultByUserId(addressBook);
        // 2、将当前地址设置默认地址
        addressBook.setIsDefault(1);
        addressBookMapper.update(addressBook);
    }

    /**
     * 删除地址
     * @param id
     */
    @Override
    public void delete(Long id) {
        log.info("删除地址：{}", id);
        addressBookMapper.deleteById(id);
    }

    @Override
    public AddressBook getById(Long id) {
//        log.info("根据id查询地址：{}", id);
//        AddressBook addressBook = AddressBook.builder()
//                .id(id)
//                .build();
//        List<AddressBook> list = addressBookMapper.list(addressBook);
//        return list.get(0);
        return addressBookMapper.getById(id);
    }
}

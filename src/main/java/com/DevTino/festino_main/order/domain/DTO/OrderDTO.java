package com.DevTino.festino_main.order.domain.DTO;

import com.DevTino.festino_main.order.domain.*;
import com.DevTino.festino_main.order.others.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {
    Integer orderNum;
    UUID orderId;
    UUID boothId;
    OrderType orderType;
    Integer tableNum;
    Integer date;
    String userName;
    String phoneNum;
    Integer totalPrice;
    LocalDateTime createAt;
    Boolean isCoupon;
    Boolean isDeposit;

    @Convert(converter = StringListConverter.class)
    List<Map<String, Object>> menuInfo;

    public static OrderDTO fromComputerOrderDAO(ComputerOrderDAO computerOrderDAO) {
        return OrderDTO.builder()
                .orderNum(computerOrderDAO.getOrderNum())
                .orderId(computerOrderDAO.getOrderId())
                .boothId(computerOrderDAO.getBoothId())
                .orderType(computerOrderDAO.getOrderType())
                .tableNum(computerOrderDAO.getTableNum())
                .date(computerOrderDAO.getDate())
                .userName(computerOrderDAO.getUserName())
                .phoneNum(computerOrderDAO.getPhoneNum())
                .totalPrice(computerOrderDAO.getTotalPrice())
                .createAt(computerOrderDAO.getCreateAt())
                .isCoupon(computerOrderDAO.getIsCoupon())
                .isDeposit(computerOrderDAO.getIsDeposit())
                .menuInfo(computerOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromGameOrderDAO(GameOrderDAO gameOrderDAO) {
        return OrderDTO.builder()
                .orderNum(gameOrderDAO.getOrderNum())
                .orderId(gameOrderDAO.getOrderId())
                .boothId(gameOrderDAO.getBoothId())
                .orderType(gameOrderDAO.getOrderType())
                .tableNum(gameOrderDAO.getTableNum())
                .date(gameOrderDAO.getDate())
                .userName(gameOrderDAO.getUserName())
                .phoneNum(gameOrderDAO.getPhoneNum())
                .totalPrice(gameOrderDAO.getTotalPrice())
                .createAt(gameOrderDAO.getCreateAt())
                .isCoupon(gameOrderDAO.getIsCoupon())
                .isDeposit(gameOrderDAO.getIsDeposit())
                .menuInfo(gameOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromNanoOrderDAO(NanoOrderDAO nanoOrderDAO) {
        return OrderDTO.builder()
                .orderNum(nanoOrderDAO.getOrderNum())
                .orderId(nanoOrderDAO.getOrderId())
                .boothId(nanoOrderDAO.getBoothId())
                .orderType(nanoOrderDAO.getOrderType())
                .tableNum(nanoOrderDAO.getTableNum())
                .date(nanoOrderDAO.getDate())
                .userName(nanoOrderDAO.getUserName())
                .phoneNum(nanoOrderDAO.getPhoneNum())
                .totalPrice(nanoOrderDAO.getTotalPrice())
                .createAt(nanoOrderDAO.getCreateAt())
                .isCoupon(nanoOrderDAO.getIsCoupon())
                .isDeposit(nanoOrderDAO.getIsDeposit())
                .menuInfo(nanoOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromNewMaterialOrderDAO(NewMaterialOrderDAO newMaterialOrderDAO) {
        return OrderDTO.builder()
                .orderNum(newMaterialOrderDAO.getOrderNum())
                .orderId(newMaterialOrderDAO.getOrderId())
                .boothId(newMaterialOrderDAO.getBoothId())
                .orderType(newMaterialOrderDAO.getOrderType())
                .tableNum(newMaterialOrderDAO.getTableNum())
                .date(newMaterialOrderDAO.getDate())
                .userName(newMaterialOrderDAO.getUserName())
                .phoneNum(newMaterialOrderDAO.getPhoneNum())
                .totalPrice(newMaterialOrderDAO.getTotalPrice())
                .createAt(newMaterialOrderDAO.getCreateAt())
                .isCoupon(newMaterialOrderDAO.getIsCoupon())
                .isDeposit(newMaterialOrderDAO.getIsDeposit())
                .menuInfo(newMaterialOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromDesignOrderDAO(DesignOrderDAO designOrderDAO) {
        return OrderDTO.builder()
                .orderNum(designOrderDAO.getOrderNum())
                .orderId(designOrderDAO.getOrderId())
                .boothId(designOrderDAO.getBoothId())
                .orderType(designOrderDAO.getOrderType())
                .tableNum(designOrderDAO.getTableNum())
                .date(designOrderDAO.getDate())
                .userName(designOrderDAO.getUserName())
                .phoneNum(designOrderDAO.getPhoneNum())
                .totalPrice(designOrderDAO.getTotalPrice())
                .createAt(designOrderDAO.getCreateAt())
                .isCoupon(designOrderDAO.getIsCoupon())
                .isDeposit(designOrderDAO.getIsDeposit())
                .menuInfo(designOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromMachineOrderDAO(MachineOrderDAO machineOrderDAO) {
        return OrderDTO.builder()
                .orderNum(machineOrderDAO.getOrderNum())
                .orderId(machineOrderDAO.getOrderId())
                .boothId(machineOrderDAO.getBoothId())
                .orderType(machineOrderDAO.getOrderType())
                .tableNum(machineOrderDAO.getTableNum())
                .date(machineOrderDAO.getDate())
                .userName(machineOrderDAO.getUserName())
                .phoneNum(machineOrderDAO.getPhoneNum())
                .totalPrice(machineOrderDAO.getTotalPrice())
                .createAt(machineOrderDAO.getCreateAt())
                .isCoupon(machineOrderDAO.getIsCoupon())
                .isDeposit(machineOrderDAO.getIsDeposit())
                .menuInfo(machineOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromElectronicsOrderDAO(ElectronicsOrderDAO electronicsOrderDAO) {
        return OrderDTO.builder()
                .orderNum(electronicsOrderDAO.getOrderNum())
                .orderId(electronicsOrderDAO.getOrderId())
                .boothId(electronicsOrderDAO.getBoothId())
                .orderType(electronicsOrderDAO.getOrderType())
                .tableNum(electronicsOrderDAO.getTableNum())
                .date(electronicsOrderDAO.getDate())
                .userName(electronicsOrderDAO.getUserName())
                .phoneNum(electronicsOrderDAO.getPhoneNum())
                .totalPrice(electronicsOrderDAO.getTotalPrice())
                .createAt(electronicsOrderDAO.getCreateAt())
                .isCoupon(electronicsOrderDAO.getIsCoupon())
                .isDeposit(electronicsOrderDAO.getIsDeposit())
                .menuInfo(electronicsOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromEnergyOrderDAO(EnergyOrderDAO energyOrderDAO) {
        return OrderDTO.builder()
                .orderNum(energyOrderDAO.getOrderNum())
                .orderId(energyOrderDAO.getOrderId())
                .boothId(energyOrderDAO.getBoothId())
                .orderType(energyOrderDAO.getOrderType())
                .tableNum(energyOrderDAO.getTableNum())
                .date(energyOrderDAO.getDate())
                .userName(energyOrderDAO.getUserName())
                .phoneNum(energyOrderDAO.getPhoneNum())
                .totalPrice(energyOrderDAO.getTotalPrice())
                .createAt(energyOrderDAO.getCreateAt())
                .isCoupon(energyOrderDAO.getIsCoupon())
                .isDeposit(energyOrderDAO.getIsDeposit())
                .menuInfo(energyOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromMechatronicsOrderDAO(MechatronicsOrderDAO mechatronicsOrderDAO) {
        return OrderDTO.builder()
                .orderNum(mechatronicsOrderDAO.getOrderNum())
                .orderId(mechatronicsOrderDAO.getOrderId())
                .boothId(mechatronicsOrderDAO.getBoothId())
                .orderType(mechatronicsOrderDAO.getOrderType())
                .tableNum(mechatronicsOrderDAO.getTableNum())
                .date(mechatronicsOrderDAO.getDate())
                .userName(mechatronicsOrderDAO.getUserName())
                .phoneNum(mechatronicsOrderDAO.getPhoneNum())
                .totalPrice(mechatronicsOrderDAO.getTotalPrice())
                .createAt(mechatronicsOrderDAO.getCreateAt())
                .isCoupon(mechatronicsOrderDAO.getIsCoupon())
                .isDeposit(mechatronicsOrderDAO.getIsDeposit())
                .menuInfo(mechatronicsOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromBiochemistryOrderDAO(BiochemistryOrderDAO biochemistryOrderDAO) {
        return OrderDTO.builder()
                .orderNum(biochemistryOrderDAO.getOrderNum())
                .orderId(biochemistryOrderDAO.getOrderId())
                .boothId(biochemistryOrderDAO.getBoothId())
                .orderType(biochemistryOrderDAO.getOrderType())
                .tableNum(biochemistryOrderDAO.getTableNum())
                .date(biochemistryOrderDAO.getDate())
                .userName(biochemistryOrderDAO.getUserName())
                .phoneNum(biochemistryOrderDAO.getPhoneNum())
                .totalPrice(biochemistryOrderDAO.getTotalPrice())
                .createAt(biochemistryOrderDAO.getCreateAt())
                .isCoupon(biochemistryOrderDAO.getIsCoupon())
                .isDeposit(biochemistryOrderDAO.getIsDeposit())
                .menuInfo(biochemistryOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromMachinedesignOrderDAO(MachinedesignOrderDAO machinedesignOrderDAO) {
        return OrderDTO.builder()
                .orderNum(machinedesignOrderDAO.getOrderNum())
                .orderId(machinedesignOrderDAO.getOrderId())
                .boothId(machinedesignOrderDAO.getBoothId())
                .orderType(machinedesignOrderDAO.getOrderType())
                .tableNum(machinedesignOrderDAO.getTableNum())
                .date(machinedesignOrderDAO.getDate())
                .userName(machinedesignOrderDAO.getUserName())
                .phoneNum(machinedesignOrderDAO.getPhoneNum())
                .totalPrice(machinedesignOrderDAO.getTotalPrice())
                .createAt(machinedesignOrderDAO.getCreateAt())
                .isCoupon(machinedesignOrderDAO.getIsCoupon())
                .isDeposit(machinedesignOrderDAO.getIsDeposit())
                .menuInfo(machinedesignOrderDAO.getMenuInfo())
                .build();
    }

    public static OrderDTO fromBusinessOrderDAO(BusinessOrderDAO businessOrderDAO) {
        return OrderDTO.builder()
                .orderNum(businessOrderDAO.getOrderNum())
                .orderId(businessOrderDAO.getOrderId())
                .boothId(businessOrderDAO.getBoothId())
                .orderType(businessOrderDAO.getOrderType())
                .tableNum(businessOrderDAO.getTableNum())
                .date(businessOrderDAO.getDate())
                .userName(businessOrderDAO.getUserName())
                .phoneNum(businessOrderDAO.getPhoneNum())
                .totalPrice(businessOrderDAO.getTotalPrice())
                .createAt(businessOrderDAO.getCreateAt())
                .isCoupon(businessOrderDAO.getIsCoupon())
                .isDeposit(businessOrderDAO.getIsDeposit())
                .menuInfo(businessOrderDAO.getMenuInfo())
                .build();
    }
}

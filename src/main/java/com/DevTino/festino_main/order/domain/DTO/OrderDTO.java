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
                .orderId(computerOrderDAO.getOrderId())
                .boothId(computerOrderDAO.getBoothId())
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
                .orderId(gameOrderDAO.getOrderId())
                .boothId(gameOrderDAO.getBoothId())
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
                .orderId(nanoOrderDAO.getOrderId())
                .boothId(nanoOrderDAO.getBoothId())
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
                .orderId(newMaterialOrderDAO.getOrderId())
                .boothId(newMaterialOrderDAO.getBoothId())
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
                .orderId(designOrderDAO.getOrderId())
                .boothId(designOrderDAO.getBoothId())
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
                .orderId(machineOrderDAO.getOrderId())
                .boothId(machineOrderDAO.getBoothId())
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
                .orderId(electronicsOrderDAO.getOrderId())
                .boothId(electronicsOrderDAO.getBoothId())
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
                .orderId(energyOrderDAO.getOrderId())
                .boothId(energyOrderDAO.getBoothId())
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
                .orderId(mechatronicsOrderDAO.getOrderId())
                .boothId(mechatronicsOrderDAO.getBoothId())
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
                .orderId(biochemistryOrderDAO.getOrderId())
                .boothId(biochemistryOrderDAO.getBoothId())
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
                .orderId(machinedesignOrderDAO.getOrderId())
                .boothId(machinedesignOrderDAO.getBoothId())
                .userName(machinedesignOrderDAO.getUserName())
                .phoneNum(machinedesignOrderDAO.getPhoneNum())
                .totalPrice(machinedesignOrderDAO.getTotalPrice())
                .createAt(machinedesignOrderDAO.getCreateAt())
                .isCoupon(machinedesignOrderDAO.getIsCoupon())
                .isDeposit(machinedesignOrderDAO.getIsDeposit())
                .menuInfo(machinedesignOrderDAO.getMenuInfo())
                .build();
    }
}

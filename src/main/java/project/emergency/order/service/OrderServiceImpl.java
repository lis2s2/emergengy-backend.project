package project.emergency.order.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;
import project.emergency.order.dto.OrderDTO;
import project.emergency.order.entity.Order;
import project.emergency.order.repository.OrderRepository;
import project.emergency.orderItem.dto.OrderItemDTO;
import project.emergency.orderItem.entity.OrderItem;
import project.emergency.orderItem.repository.OrderItemRepository;
import project.emergency.shop.entity.Shop;
import project.emergency.shop.repositorty.ShopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ShopRepository shopRepository;


    @Override
    public OrderDTO getOrderDetails(int orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("주문을 가져오는 중 오류 발생"));
        return entityToDto(order);
    }

//    @Override
//    @Transactional
//    public OrderDTO saveOrder(OrderDTO orderDTO) {
//        System.out.println("받은 OrderDTO: " + orderDTO.toString());
//
//        Order order = dtoToEntity(orderDTO);
//
//        for (OrderItem orderItem : order.getOrderItems()) {
//            int prodNo = orderItem.getShop().getProdNo();
//
//            Shop shop = shopRepository.findById(prodNo)
//                    .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. 상품번호:" + prodNo));
//            orderItem.setShop(shop);
//            orderItem.setOrder(order);
//        }
//
//        Order savedOrder = orderRepository.save(order);
//        return entityToDto(savedOrder);
//    }

    @Override
    @Transactional
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        System.out.println("받은 OrderDTO: " + orderDTO.toString());

        // Order DTO를 엔티티로 변환
        Order order = dtoToEntity(orderDTO);

        // OrderItems를 설정
        for (OrderItem orderItem : order.getOrderItems()) {
            int prodNo = orderItem.getShop().getProdNo();

            // Shop 엔티티 조회 및 설정
            Shop shop = shopRepository.findById(prodNo)
                    .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. 상품번호:" + prodNo));
            orderItem.setShop(shop);
            orderItem.setOrder(order);
        }

        // Member 엔티티 조회
        Member member = order.getMember();
        int usedPoint = order.getUsedPoint();

        // Member의 포인트 차감 로직
        if (member.getMemPoint() < usedPoint) {
            throw new RuntimeException("사용할 포인트가 부족합니다.");
        }
        member.setMemPoint(member.getMemPoint() - usedPoint);
        memberRepository.save(member);

        // Order 저장
        Order savedOrder = orderRepository.save(order);

        return entityToDto(savedOrder);
    }

    private Order dtoToEntity(OrderDTO dto) {
        Member member = memberRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        Order order = Order.builder()
                .member(member)
                .customerName(dto.getCustomerName())
                .address(dto.getAddress())
                .phoneNum(dto.getPhoneNum())
                .detailedAddress(dto.getDetailedAddress())
                .postalCode(dto.getPostalCode())
                .usedPoint(dto.getUsedPoint())
                .totalAmount(dto.getTotalAmount())
                .build();

        List<OrderItem> orderItems = dto.getOrderItems().stream()
                .map(this::orderItemDtoToEntity)
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);

        return order;
    }

    private OrderItem orderItemDtoToEntity(OrderItemDTO dto) {
        Order order = Order.builder().orderNo(dto.getOrderNo()).build();
        Shop shop = Shop.builder().prodNo(dto.getProductNo()).build();

        return OrderItem.builder()
                .order(order)
                .shop(shop)
                .prodCount(dto.getCount())
                .productPrice(dto.getProductPrice())
                .totalPrice(dto.getTotalPrice())
                .build();
    }

    private OrderDTO entityToDto(Order order) {
        return OrderDTO.builder()
                .orderNo(order.getOrderNo())
                .customerId(order.getMember().getMemId())
                .usedPoint(order.getUsedPoint())
                .totalAmount(order.getTotalAmount())
                .customerName(order.getCustomerName())
                .address(order.getAddress())
                .phoneNum(order.getPhoneNum())
                .detailedAddress(order.getDetailedAddress())
                .postalCode(order.getPostalCode())
                .orderItems(orderItemRepository.findByOrderOrderNo(order.getOrderNo()).stream().map(this::entityToDto).collect(Collectors.toList()))
                .build();
    }

    private OrderItemDTO entityToDto(OrderItem entity) {
        return OrderItemDTO.builder()
                .orderItemNo(entity.getOrderItemNo())
                .orderNo(entity.getOrder().getOrderNo())
                .productNo(entity.getShop().getProdNo())
                .count(entity.getProdCount())
                .productPrice(entity.getShop().getProdPrice())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

}

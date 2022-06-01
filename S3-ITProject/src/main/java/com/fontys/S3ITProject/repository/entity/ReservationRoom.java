package com.fontys.s3itproject.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reservation_room")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Room room;
}

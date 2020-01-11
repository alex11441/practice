package com.conaxgames.practice.queue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class QueueEntry {

    /**
     * The time this entry was created.
     */
    private final long startTime = System.currentTimeMillis();

    /**
     * The corresponding queue for this entry.
     */
    private final KitQueue kitQueue;

    /**
     * The members that are a part of this entry.
     */
    private final Set<UUID> members;

    /**
     * If this is not for a ranked queue, this will not be assigned.
     * If a party match, the party members' average elo.
     * If a solo match, the member's elo.
     */
    private int elo = 0;

}

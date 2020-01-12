package com.conaxgames.practice.queue;

import com.conaxgames.practice.Practice;
import com.conaxgames.practice.kit.Kit;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class KitQueue {

    /**
     * The designated {@link Kit} this queue is for.
     */
    private final Kit kit;

    /**
     * Whether or not this queue is a ranked queue.
     */
    private final boolean isRanked;

    /**
     * Entries of players currently queuing for the
     * specified kit
     */
    private final List<QueueEntry> queueEntries = new ArrayList<>();

    /**
     * Handles queue logic, mainly creating matches.
     */
    public void tick() {
        List<QueueEntry> queueEntriesCopy = new ArrayList<>(queueEntries);

        // TODO: Check elo if ranked queue

        while (queueEntriesCopy.size() >= 2 && queueEntriesCopy.size() % 2 == 0) {
            QueueEntry entry1 = queueEntriesCopy.remove(0);
            QueueEntry entry2 = queueEntriesCopy.remove(0);

            // TODO:
            // Get the match members after it's created and remove them
            // w/ QueueManager#removeFromQueue for code cleanliness

            List<Player> participants = new ArrayList<>();
            participants.addAll(entry1.getPlayers());
            participants.addAll(entry2.getPlayers());

            participants.forEach(player -> {
                player.sendMessage("queueeeeeeeeed!! you would be in a match right now");
                Practice.getInstance().getQueueManager().removeFromQueue(player);
            });
        }
    }

    /**
     * Adds an entry to the queue.
     *
     * @param entry the entry to add
     */
    public void addEntry(QueueEntry entry) {
        queueEntries.add(entry);
    }

    /**
     * Removes an entry from the queue.
     *
     * @param entry the entry to remove
     */
    public void removeEntry(QueueEntry entry) {
        queueEntries.remove(entry);
    }
}

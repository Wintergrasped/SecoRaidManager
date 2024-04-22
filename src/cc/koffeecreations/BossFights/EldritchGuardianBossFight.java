package cc.koffeecreations.BossFights;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import cc.koffeecreations.Loot.LootManager;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EldritchGuardianBossFight implements Listener {
    private JavaPlugin plugin;
    private LivingEntity boss;
    private boolean inCombat = false;
    private int phase = 0;

    // Configurable variables
    private static final double INITIAL_HEALTH = 1000;
    private static final double INITIAL_DAMAGE = 1500;
    private static final double INITIAL_ARMOR = 5800;
    private static final double INITIAL_ARMOR_TOUGHNESS = 6210;
    
    private static final int DAMAGE_INCREASE_EFFECT = 2;
    private static final int PHASE_ONE_THRESHOLD = 75; // Percentage
    private static final int PHASE_TWO_THRESHOLD = 50; // Percentage
    private static final int PHASE_THREE_THRESHOLD = 25; // Percentage
    private static final int REGENERATION_DURATION = 6000;
    private static final int REGENERATION_LEVEL = 1;
    
    private static final int DAMAGE_REDUCTION_LEVEL = 90;
    
    private static final int SHADOW_CLONE_COUNT = 6;
    private static final int SHADOW_CLONE_HEALTH = 50;
    private static final int SHADOW_CLONE_ARMOR = 350;
    private static final int SHADOW_CLONE_ARMOR_TOUGHNESS = 20;
    
    private static final int PAHSE_THREE_RESET_TIME = 10;

    
    private boolean PHASE_ONE_STARTED = false;
    private ArrayList<Entity> adds = new ArrayList<Entity>();
	private BukkitTask fireTask;
	private int PhaseThreeRuns = 0;
	private Random random;
	private Player targetPlayer;
    
    public EldritchGuardianBossFight(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void summonBoss(Location location) {
        boss = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
        boss.setCustomName("Eldritch Guardian");
        boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(INITIAL_HEALTH);
		boss.setHealth(INITIAL_HEALTH);
        boss.setRemoveWhenFarAway(false);
        boss.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, DAMAGE_INCREASE_EFFECT));
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40);
        boss.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(40);
        boss.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(40);
        
        AttributeModifier dmgMod = new AttributeModifier(boss.getUniqueId(), "generic.attackDamage", INITIAL_DAMAGE, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armMod = new AttributeModifier(boss.getUniqueId(), "generic.armor", INITIAL_ARMOR, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier armToughMod = new AttributeModifier(boss.getUniqueId(), "generic.armortoughness", INITIAL_ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER);
        
        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(dmgMod);
        boss.getAttribute(Attribute.GENERIC_ARMOR).addModifier(armMod);
        boss.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).addModifier(armToughMod);
        
        inCombat = true;
        startCombatLogic();
    }

    private void startCombatLogic() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!boss.isDead() && inCombat) {
                    double healthPercent = boss.getHealth() / boss.getMaxHealth() * 100;
                    managePhases(healthPercent);
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Run task every second
    }
    

    private void addsCombatLogic() {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Check if the list of adds is empty or the boss is dead
                if (adds.isEmpty() || boss.isDead()) {
                    this.cancel(); // Cancel this task if no adds left or the boss is dead
                    return; // Exit the method early
                }

                Iterator<Entity> iterator = adds.iterator();  // Use an iterator for safe removal
                while (iterator.hasNext()) {
                    Entity e = iterator.next();
                    if (e.isDead()) {  // Check if the entity is dead
                        iterator.remove();  // Safely remove the entity using iterator
                    } else {
                        // Enhance the boss's stats based on remaining adds
                        double newHealth = Math.min(boss.getHealth() + 50, boss.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        double newAttackDamage = boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() + 50;

                        // Update boss's health and attack damage
                        boss.setHealth(newHealth);
                        boss.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(newAttackDamage);
                    }
                }
            }
        }.runTaskTimer(plugin, 100L, 100L);  // Run task every 5 seconds (100 ticks)
    }



    private void managePhases(double healthPercent) {
        if (healthPercent <= PHASE_ONE_THRESHOLD && phase < 1) {
            phase = 1;
            boss.getWorld().getPlayers().forEach(p -> p.sendMessage("The Eldritch Guardian strengthens its resolve!"));
            boss.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, REGENERATION_DURATION, REGENERATION_LEVEL));
            spawnShadowClones(boss.getLocation(), SHADOW_CLONE_COUNT);
            addsCombatLogic();
        } else if (healthPercent <= PHASE_TWO_THRESHOLD && phase < 2) {
            phase = 2;
            boss.getWorld().getPlayers().forEach(p -> p.sendMessage("The Eldritch Guardian warps reality around you!"));
            // Implement reality warp logic
        } else if (healthPercent <= PHASE_THREE_THRESHOLD && phase < 3) {
            phase = 3;
            boss.getWorld().getPlayers().forEach(p -> p.sendMessage("The Eldritch Guardian attempts to seize control of your mind!"));
            startPhaseThree(playersinArea(boss.getLocation(), 300));
        }
    }

    private void spawnShadowClones(Location location, int count) {
        for (int i = 0; i < count; i++) {
            LivingEntity clone = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
            clone.setCustomName("Shadow Clone");
            clone.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SHADOW_CLONE_HEALTH);
    		clone.setHealth(SHADOW_CLONE_HEALTH);
    		
    		AttributeModifier armMod = new AttributeModifier(boss.getUniqueId(), "generic.armor", SHADOW_CLONE_ARMOR, AttributeModifier.Operation.ADD_NUMBER);
            AttributeModifier armToughMod = new AttributeModifier(boss.getUniqueId(), "generic.armortoughness", SHADOW_CLONE_ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_NUMBER);
    		
            adds.add(clone);
        }
    }
    
    
    
    
    
    
    
    
    
    public void startPhaseThree(List<Player> players) {
        // Randomly select a player to be the target
        random = new Random();
        targetPlayer = players.get(random.nextInt(players.size()));
        
        // Announce the target
        Bukkit.broadcastMessage(targetPlayer.getName() + " will be consumed by the shadows!");

        // Start the repeating task
        fireTask = new BukkitRunnable() {
            private Location lastLocation = null;

            @Override
            public void run() {
            	PhaseThreeRuns++;
            	if (PhaseThreeRuns >= PAHSE_THREE_RESET_TIME) {
            		PhaseThreeRuns = 0;
                	random = new Random();
                	targetPlayer = players.get(random.nextInt(players.size()));
                	Bukkit.broadcastMessage(targetPlayer.getName() + " will be consumed by the shadows!");
                }
                Location currentLocation = targetPlayer.getLocation();
                if (lastLocation != null) {
                	lastLocation.getBlock().setType(Material.SOUL_FIRE);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> lastLocation.getBlock().setType(Material.AIR), 200L); // Fire disappears after 10 seconds
                }
                // Update last known location
                lastLocation = currentLocation.clone();
            }
        }.runTaskTimer(plugin, 0L, 20L); // Check every second
        
    }

    public void stopPhaseThree() {
        if (fireTask != null) {
            fireTask.cancel();
        }
    }

    
    
    
    
    public List<Player> playersinArea(Location L, int Radius) {
		
		List<Player> pl = new ArrayList();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (withinDistance(p.getLocation(), L, Radius)) {
				pl.add(p);
			}
		}
		
		return pl;
	}

	public boolean withinDistance(Location loc1, Location loc2, double distance) {
		double xDiff = loc1.getX() - loc2.getX();
		double yDiff = loc1.getY() - loc2.getY();
		double zDiff = loc1.getZ() - loc2.getZ();
		double distanceSquared = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
		return distanceSquared <= distance * distance;
	}
    
    
    
    
    
    
    
    

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
    	
    	
    	
        if (event.getEntity().equals(boss)) {
        	double red = 100-DAMAGE_REDUCTION_LEVEL;
        	red = red/100;
        	
        	event.setDamage((event.getDamage()*red));
            if (event.getDamage() >= (INITIAL_HEALTH*0.21)) {
            	event.setDamage((INITIAL_HEALTH*0.08));
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().equals(boss)) {
            inCombat = false;
            stopPhaseThree();
            Bukkit.broadcastMessage("The Eldritch Guardian has been defeated!");
            event.getDrops().clear(); // Clear default drops
            event.getDrops().add(new ItemStack(Material.DIAMOND, 20)); // Custom drops
            event.getDrops().add(new LootManager().getRandomBossDrop());
            event.getDrops().add(new LootManager().getRandomBossDrop());
            event.getDrops().add(new LootManager().getRandomBossDrop());
            event.getDrops().add(new LootManager().getRandomBossDrop());
        }
    }
}

package cc.koffeecreations.Loot;

import java.util.Random;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.koffeecreations.gear.GearManager;
import cc.koffeecreations.main.Items.InfusedArmor;
import cc.koffeecreations.main.Items.InfusedWeapons;
import cc.koffeecreations.main.econ.Econ;
import cc.koffeecreations.main.skills.skills;

public class LootManager {


		
		GearManager GM = new GearManager();
		
		boolean debug = true;
		
		int randomizer = 0;
		
		
		@EventHandler
		public void onKill(EntityDeathEvent e) {
			
			
		}
		
		
		public ItemStack getRandomDrop() {
			ItemStack IS = new ItemStack(Material.BONE, 0); 
			
			
			
			if (random(1,7600) == 1352) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random(1,15));
			}
			
			if (random2(1,7600) == 1357) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.DURABILITY, random(4,20));
			}
			
			if (random(1,9000) == 9169) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random(6,20));
			}
			
			if (random2(1,7693) == 2243) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, random(1,15));
			}
			
			if (random(1,9693) == 3103) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, random(1,15));
			}
			
			if (random2(1,9963) == 1284) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, random(1,15));
			}
			
			if (random(1,90325) == 3150) {
				IS = InfusedArmor.RegenNBoots();
			}
			
			if (random2(1,90325) == 3300) {
				IS = InfusedArmor.RegenNHelm();
			}
			
			if (random(1,90325) == 4600) {
				IS = InfusedArmor.RegenNPants();
			}
			
			if (random2(1,90325) == 3290) {
				IS = InfusedArmor.RegenNChest();
			}
			
			if (random(1,90340) == 5230) {
				IS = InfusedArmor.StrNHelm();
			}
			
			if (random2(1,90340) == 4350) {
				IS = InfusedArmor.StrNChest();
			}
			
			if (random(1,90340) == 2700) {
				IS = InfusedArmor.StrNPants();
			}
			
			if (random2(1,90345) == 5390) {
				IS = InfusedArmor.StrNBoots();
			}
			
			if (random(1,90345) == 2109) {
				IS = InfusedWeapons.StrNSword();
			}
			
			if (random2(1,90345) == 5111) {
				IS = InfusedWeapons.RegenNSword();
			}
			
			if (random(1,90345) == 6088) {
				IS = InfusedWeapons.MightNSword();
			}
			
			return IS;
		}
		
		
		
		public ItemStack getRandomBossDrop() {
			ItemStack IS = new ItemStack(Material.BONE, 0); 
			ItemMeta IM = IS.getItemMeta();
			
			if (random(1,10)==8) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random(1,15));
			}
			
			if (random2(1,100)==69) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random(6,20));
			}
			
			if (random(1,100)==73) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.DURABILITY, random(6,20));
			}
			
			if (random2(1,100)==88) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, random(10,25));
			}
			
			if (random(1,33)== 24) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, random(1,15));
			}
			
			if (random2(1,73)== 70) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, random(10,25));
			}
			
			if (random(1,33)== 24) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, random(1,15));
			}
			
			if (random2(1,133)== 93) {
				IS.setType(Material.ENCHANTED_BOOK);
				IS.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, random(10,25));
			}
			
			if (random(1,25)==15) {
				IS = InfusedArmor.RegenNBoots();
			}
			
			if (random2(1,25)==3) {
				IS = InfusedArmor.RegenNHelm();
			}
			
			if (random(1,25)==6) {
				IS = InfusedArmor.RegenNPants();
			}
			
			if (random2(1,25)==20) {
				IS = InfusedArmor.RegenNChest();
			}
			
			if (random(1,40)==23) {
				IS = InfusedArmor.StrNHelm();
			}
			
			if (random2(1,40)==35) {
				IS = InfusedArmor.StrNChest();
			}
			
			if (random(1,40)==7) {
				IS = InfusedArmor.StrNPants();
			}
			
			if (random2(1,45)==39) {
				IS = InfusedArmor.StrNBoots();
			}
			
			
			//Weapons
			if (random(1,45)==39) {
				IS = InfusedWeapons.StrNSword();
			}
			
			if (random2(1,45)==40) {
				IS = InfusedWeapons.RegenNSword();
			}
			
			if (random(1,45)==42) {
				IS = InfusedWeapons.MightNSword();
			}
			
			return IS;
		}
		
		public int calculateBaseGearLevel(Player p) {
			
			int bgl = 0;
			
			ItemStack Weapon = p.getInventory().getItemInMainHand();
			ItemStack Helmet = p.getInventory().getHelmet();
			ItemStack Chest = p.getInventory().getChestplate();
			ItemStack Pants = p.getInventory().getLeggings();
			ItemStack Boots = p.getInventory().getBoots();
			
			if (!(Weapon == null)) {
				
				if (Weapon.getType().equals(Material.NETHERITE_SWORD)) {
					bgl = 15;
				}else if (Weapon.getType().equals(Material.DIAMOND_SWORD)) {
					bgl = 10;
				}else if (Weapon.getType().equals(Material.IRON_SWORD)) {
					bgl = 5;
				}
				
				if (!(Weapon.getEnchantments().isEmpty())) {
					bgl = bgl+5;
				}
				
			}
			
			if (!(Helmet == null)) {
				
				if (Helmet.getType().equals(Material.NETHERITE_HELMET)) {
					bgl = 15;
				}else if (Helmet.getType().equals(Material.DIAMOND_HELMET)) {
					bgl = 10;
				}else if (Weapon.getType().equals(Material.IRON_HELMET)) {
					bgl = 5;
				}
				
				if (!(Helmet.getEnchantments().isEmpty())) {
					bgl = bgl+5;
				}
				
			}
			
			if (!(Chest == null)) {
				
				if (Chest.getType().equals(Material.NETHERITE_CHESTPLATE)) {
					bgl = 15;
				}else if (Chest.getType().equals(Material.DIAMOND_CHESTPLATE)) {
					bgl = 10;
				}else if (Chest.getType().equals(Material.IRON_CHESTPLATE)) {
					bgl = 5;
				}
				
				if (!(Chest.getEnchantments().isEmpty())) {
					bgl = bgl+5;
				}
				
			}
			
			if (!(Pants == null)) {
				
				if (Pants.getType().equals(Material.NETHERITE_LEGGINGS)) {
					bgl = 15;
				}else if (Pants.getType().equals(Material.DIAMOND_LEGGINGS)) {
					bgl = 10;
				}else if (Pants.getType().equals(Material.IRON_LEGGINGS)) {
					bgl = 5;
				}
				
				if (!(Chest.getEnchantments().isEmpty())) {
					bgl = bgl+5;
				}
				
			}
			
			if (!(Boots == null)) {
				
				if (Boots.getType().equals(Material.NETHERITE_BOOTS)) {
					bgl = 15;
				}else if (Boots.getType().equals(Material.DIAMOND_BOOTS)) {
					bgl = 10;
				}else if (Boots.getType().equals(Material.IRON_BOOTS)) {
					bgl = 5;
				}
				
				if (!(Chest.getEnchantments().isEmpty())) {
					bgl = bgl+5;
				}
				
			}
				
			
			
			return bgl;
		}
		
		public int random(int min, int max) {

	        Random rand = new Random();
	        rand.setSeed(Bukkit.getServer().getWorld("World").getFullTime()+Bukkit.getBannedPlayers().size()+Bukkit.getOnlinePlayers().size()+System.currentTimeMillis()+System.nanoTime()+Bukkit.getOnlinePlayers().size()+Bukkit.getOperators().size()+Bukkit.getPort()+Bukkit.getOfflinePlayers().length+Bukkit.getIdleTimeout()+Bukkit.getIPBans().size()+Bukkit.getWorlds().size()+Bukkit.getName().getBytes().length+Bukkit.getWorld("world").getSeed());
	        int randomNum = rand.nextInt((max - min) + 1) + min;

	        return randomNum;
	    }
		
		public int random2(int min, int max) {

			if (randomizer >= 1000000) {
				randomizer = random(1, 5000);
			}
			
	        Random rand = new Random();
	        rand.setSeed(random(1,2500)+Bukkit.getServer().getWorld("World").getFullTime()+Bukkit.getBannedPlayers().size()+Bukkit.getOnlinePlayers().size()+System.currentTimeMillis()+System.nanoTime()+Bukkit.getOnlinePlayers().size()+Bukkit.getOperators().size()+Bukkit.getPort()+Bukkit.getOfflinePlayers().length+Bukkit.getIdleTimeout()+Bukkit.getIPBans().size()+Bukkit.getWorlds().size()+Bukkit.getName().getBytes().length+Bukkit.getWorld("world").getSeed()+randomizer);
	        int randomNum = rand.nextInt((max - min) + 1) + min;

	        return randomNum;
	    }
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

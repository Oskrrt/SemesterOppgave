package com.sample.DAL;

import com.sample.DAL.SaveFile.FileSaver;
import com.sample.DAL.SaveFile.SaveJobj;
import com.sample.Models.ComputerComponents.*;

import java.io.File;
import java.io.IOException;

public class init {
    public static void initFiles() throws IOException {
        Case c = new Case("4", "2", "20", "40", "40", 140.52, "Enhanced Cable Management. Our patented cable routing kit with pre-installed channels and straps makes wiring easy and intuitive. Fan Noise. 28 dBA", "CA-H510B-W1 - Compact ATX Mid-Tower PC Gaming Case","NZXT", "22443311" );
        CoolingSystem cs = new CoolingSystem("20", "20", 9.9, "Innovative DESIGN enables quiet and efficient ventilation. The innovative design of the fan blades improves the air flow and facilitates a highly efficient ventilation. The impeller was designed with a focus on minimizing the noise level yet delivering the desired airflow and pressure.", "F12-120 mm Standard Low Noise Case Fan", "ARCTIC", "55664477");
        Processor cpu = new Processor(199.99, "The world's most advanced processor in the desktop PC gaming segment", "Ryzen 5 3600", "AMD", "44998877", "6", "12", "4.2");
        Keyboard kb = new Keyboard(22.99, "Work for longer with long battery life. Basic AA and AAA batteries are included with the keyboard and mouse", "MK270 Wireless Keyboard", "Logitech", "22885566", "Norwegian", true);
        Monitor m = new Monitor(104.68, "Adaptive Sync is a technology that closes the gap between the graphics card's and the monitor's refresh rates, eliminating image tearing and stuttering in the process.","E248W-19203R Ultra Thin", "Sceptre", "66990022", "LED", "24", "1080p", "HDMI");
        Motherboard mb = new Motherboard(135.00, "Powered by 2nd generation AMD Ryzen AM4 and 7th generation Athlon processors to maximize connectivity and speed with dual NVMe M.2, USB 3.1 Gen2, gigabit LAN and up to 64 Gigabytes of DDR4 (3200 Megahertz)", "ROG Strix B450-F Gaming Motherboard", "ASUS", "05990022", "AM4 socket for AMD Ryzen", "DDR4-3200Mhz", "4", "2 x PCIe 3.0 x16", "1*PCIe 2.0 x4", "(M.2 Heatsink * 2)", "HDMI", "N/A", "SupremeFX S1220A", "ATX");
        Mouse mouse = new Mouse(79.99, "High performance hero 16k sensor. Logitech most accurate sensor yet with upto 16,000 dpi for the ultimate in gaming speed, accuracy and responsiveness across entire dpi range", "G502 SE Hero Gaming Mouse", "Logitech", "04775511", false);
        PowerSupply p = new PowerSupply(144.43, "New Era in Colorful Power. Take on more than just power, with RGB LED Lighting options at your fingertips", "Toughpower Grand RGB", "Thermaltake", "67859941", "Corded-electric", "240","750");
        GraphicsCard gpu = new GraphicsCard(184.99,"The much anticipated return of MSI iconic dual fan gaming Series.", "Gaming GeForce GTX 1650", "MSI", "67774822", "4", "GDRR5");
        RAM ram = new RAM(139.69, "VENGEANCE RGB PRO Series DDR4 overclocked memory lights up your PC with mesmerizing dynamic multi-zone RGB lighting, while delivering the best in DDR4 performance.", "Vengeance RGB PRO", "Corsair", "66667743","16", "3200");
        Speaker sp = new Speaker(58.83, "Versatile setup with speakers that connect easily to computers and other devices via Bluetooth wireless or 3.5mm cable", "Z207 2.0 Multi Device Stereo Speaker", "Logitech", "65777830", "AUX");
        StorageComponent sc = new StorageComponent(54.99,"Store more, compute faster, and do it confidently with the proven reliability of BarraCuda internal hard drives", "BarraCuda 2TB", "Seagate", "60004378", "2000");

        FileSaver.saveComponent(c,"computerCase");
        FileSaver.saveComponent(cs, "coolingSystem");
        FileSaver.saveComponent(cpu, "CPU");
        FileSaver.saveComponent(kb, "keyboard");
        FileSaver.saveComponent(m, "monitor");
        FileSaver.saveComponent(mb, "motherBoard");
        FileSaver.saveComponent(mouse, "mouse");
        FileSaver.saveComponent(p, "powerSupply");
        FileSaver.saveComponent(gpu, "graphicsCard");
        FileSaver.saveComponent(ram, "RAM");
        FileSaver.saveComponent(sp, "speaker");
        FileSaver.saveComponent(sc, "storageComponent");
    }
}

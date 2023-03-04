using System;
using System.Linq;

public static class TelemetryBuffer
{
    public static byte[] ToBuffer(long reading)
    {
        if (reading >= ushort.MinValue && reading <= ushort.MaxValue)
        {
            return new[] { (byte)2 }
                .Concat(BitConverter.GetBytes((ushort)reading))
                .Concat(new byte[6])
                .ToArray();
        }
        if (reading >= short.MinValue && reading < ushort.MinValue)
        {
            return new[] { (byte)254 }
                .Concat(BitConverter.GetBytes((short)reading))
                .Concat(new byte[6])
                .ToArray();
        }
        if (
            (reading > ushort.MaxValue && reading <= int.MaxValue)
            || (reading >= int.MinValue && reading < short.MinValue)
        )
        {
            return new[] { (byte)252 }
                .Concat(BitConverter.GetBytes((int)reading))
                .Concat(new byte[4])
                .ToArray();
        }
        if (reading > int.MaxValue && reading <= uint.MaxValue)
        {
            return new[] { (byte)4 }
                .Concat(BitConverter.GetBytes((uint)reading))
                .Concat(new byte[4])
                .ToArray();
        }

        return new[] { (byte)248 }.Concat(BitConverter.GetBytes((long)reading)).ToArray();
    }

    public static long FromBuffer(byte[] buffer)
    {
        if (buffer[0] == 2)
        {
            return BitConverter.ToUInt16(buffer.Take(3).ToArray(), 1);
        }
        if (buffer[0] == 254)
        {
            return BitConverter.ToInt16(buffer.Take(3).ToArray(), 1);
        }
        if (buffer[0] == 252)
        {
            return BitConverter.ToInt32(buffer.Take(5).ToArray(), 1);
        }
        if (buffer[0] == 4)
        {
            return BitConverter.ToUInt32(buffer.Take(5).ToArray(), 1);
        }
        if (buffer[0] == 248)
        {
            return BitConverter.ToInt64(buffer, 1);
        }

        return 0;
    }
}

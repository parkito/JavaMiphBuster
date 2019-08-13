package ru.siksmfp.serialization.harness.dto.cofler;


// Code generated by colf(1); DO NOT EDIT.


import static java.lang.String.format;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;


/**
 * Data bean with built-in serialization support.

 * @author generated by colf(1)
 * @see <a href="https://github.com/pascaldekloe/colfer">Colfer's home</a>
 */
@javax.annotation.Generated(value="colf(1)", comments="Colfer from schema file media.colf")
public class User implements Serializable {

	/** The upper limit for serial byte sizes. */
	public static int colferSizeMax = 16 * 1024 * 1024;

	/** The upper limit for the number of elements in a list. */
	public static int colferListMax = 64 * 1024;




	public long id;

	public String name;

	public byte[] signature;

	public Address[] addresses;


	/** Default constructor */
	public User() {
		init();
	}

	private static final byte[] _zeroBytes = new byte[0];
	private static final Address[] _zeroAddresses = new Address[0];

	/** Colfer zero values. */
	private void init() {
		name = "";
		signature = _zeroBytes;
		addresses = _zeroAddresses;
	}

	/**
	 * {@link #reset(InputStream) Reusable} deserialization of Colfer streams.
	 */
	public static class Unmarshaller {

		/** The data source. */
		protected InputStream in;

		/** The read buffer. */
		public byte[] buf;

		/** The {@link #buf buffer}'s data start index, inclusive. */
		protected int offset;

		/** The {@link #buf buffer}'s data end index, exclusive. */
		protected int i;


		/**
		 * @param in the data source or {@code null}.
		 * @param buf the initial buffer or {@code null}.
		 */
		public Unmarshaller(InputStream in, byte[] buf) {
			// TODO: better size estimation
			if (buf == null || buf.length == 0)
				buf = new byte[Math.min(User.colferSizeMax, 2048)];
			this.buf = buf;
			reset(in);
		}

		/**
		 * Reuses the marshaller.
		 * @param in the data source or {@code null}.
		 * @throws IllegalStateException on pending data.
		 */
		public void reset(InputStream in) {
			if (this.i != this.offset) throw new IllegalStateException("colfer: pending data");
			this.in = in;
			this.offset = 0;
			this.i = 0;
		}

		/**
		 * Deserializes the following object.
		 * @return the result or {@code null} when EOF.
		 * @throws IOException from the input stream.
		 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
		 * @throws InputMismatchException when the data does not match this object's schema.
		 */
		public User next() throws IOException {
			if (in == null) return null;

			while (true) {
				if (this.i > this.offset) {
					try {
						User o = new User();
						this.offset = o.unmarshal(this.buf, this.offset, this.i);
						return o;
					} catch (BufferUnderflowException e) {
					}
				}
				// not enough data

				if (this.i <= this.offset) {
					this.offset = 0;
					this.i = 0;
				} else if (i == buf.length) {
					byte[] src = this.buf;
					// TODO: better size estimation
					if (offset == 0) this.buf = new byte[Math.min(User.colferSizeMax, this.buf.length * 4)];
					System.arraycopy(src, this.offset, this.buf, 0, this.i - this.offset);
					this.i -= this.offset;
					this.offset = 0;
				}
				assert this.i < this.buf.length;

				int n = in.read(buf, i, buf.length - i);
				if (n < 0) {
					if (this.i > this.offset)
						throw new InputMismatchException("colfer: pending data with EOF");
					return null;
				}
				assert n > 0;
				i += n;
			}
		}

	}


	/**
	 * Serializes the object.
	 * All {@code null} elements in {@link #addresses} will be replaced with a {@code new} value.
	 * @param out the data destination.
	 * @param buf the initial buffer or {@code null}.
	 * @return the final buffer. When the serial fits into {@code buf} then the return is {@code buf}.
	 *  Otherwise the return is a new buffer, large enough to hold the whole serial.
	 * @throws IOException from {@code out}.
	 * @throws IllegalStateException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 */
	public byte[] marshal(OutputStream out, byte[] buf) throws IOException {
		// TODO: better size estimation
		if (buf == null || buf.length == 0)
			buf = new byte[Math.min(User.colferSizeMax, 2048)];

		while (true) {
			int i;
			try {
				i = marshal(buf, 0);
			} catch (BufferOverflowException e) {
				buf = new byte[Math.min(User.colferSizeMax, buf.length * 4)];
				continue;
			}

			out.write(buf, 0, i);
			return buf;
		}
	}

	/**
	 * Serializes the object.
	 * All {@code null} elements in {@link #addresses} will be replaced with a {@code new} value.
	 * @param buf the data destination.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferOverflowException when {@code buf} is too small.
	 * @throws IllegalStateException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 */
	public int marshal(byte[] buf, int offset) {
		int i = offset;

		try {
			if (this.id != 0) {
				long x = this.id;
				if (x < 0) {
					x = -x;
					buf[i++] = (byte) (0 | 0x80);
				} else
					buf[i++] = (byte) 0;
				for (int n = 0; n < 8 && (x & ~0x7fL) != 0; n++) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;
			}

			if (! this.name.isEmpty()) {
				buf[i++] = (byte) 1;
				int start = ++i;

				String s = this.name;
				for (int sIndex = 0, sLength = s.length(); sIndex < sLength; sIndex++) {
					char c = s.charAt(sIndex);
					if (c < '\u0080') {
						buf[i++] = (byte) c;
					} else if (c < '\u0800') {
						buf[i++] = (byte) (192 | c >>> 6);
						buf[i++] = (byte) (128 | c & 63);
					} else if (c < '\ud800' || c > '\udfff') {
						buf[i++] = (byte) (224 | c >>> 12);
						buf[i++] = (byte) (128 | c >>> 6 & 63);
						buf[i++] = (byte) (128 | c & 63);
					} else {
						int cp = 0;
						if (++sIndex < sLength) cp = Character.toCodePoint(c, s.charAt(sIndex));
						if ((cp >= 1 << 16) && (cp < 1 << 21)) {
							buf[i++] = (byte) (240 | cp >>> 18);
							buf[i++] = (byte) (128 | cp >>> 12 & 63);
							buf[i++] = (byte) (128 | cp >>> 6 & 63);
							buf[i++] = (byte) (128 | cp & 63);
						} else
							buf[i++] = (byte) '?';
					}
				}
				int size = i - start;
				if (size > User.colferSizeMax)
					throw new IllegalStateException(format("colfer: com/example/media.User.name size %d exceeds %d UTF-8 bytes", size, User.colferSizeMax));

				int ii = start - 1;
				if (size > 0x7f) {
					i++;
					for (int x = size; x >= 1 << 14; x >>>= 7) i++;
					System.arraycopy(buf, start, buf, i - size, size);

					do {
						buf[ii++] = (byte) (size | 0x80);
						size >>>= 7;
					} while (size > 0x7f);
				}
				buf[ii] = (byte) size;
			}

			if (this.signature.length != 0) {
				buf[i++] = (byte) 2;

				int size = this.signature.length;
				if (size > User.colferSizeMax)
					throw new IllegalStateException(format("colfer: com/example/media.User.signature size %d exceeds %d bytes", size, User.colferSizeMax));

				int x = size;
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				int start = i;
				i += size;
				System.arraycopy(this.signature, 0, buf, start, size);
			}

			if (this.addresses.length != 0) {
				buf[i++] = (byte) 3;
				Address[] a = this.addresses;

				int x = a.length;
				if (x > User.colferListMax)
					throw new IllegalStateException(format("colfer: com/example/media.User.addresses length %d exceeds %d elements", x, User.colferListMax));
				while (x > 0x7f) {
					buf[i++] = (byte) (x | 0x80);
					x >>>= 7;
				}
				buf[i++] = (byte) x;

				for (int ai = 0; ai < a.length; ai++) {
					Address o = a[ai];
					if (o == null) {
						o = new Address();
						a[ai] = o;
					}
					i = o.marshal(buf, i);
				}
			}

			buf[i++] = (byte) 0x7f;
			return i;
		} catch (ArrayIndexOutOfBoundsException e) {
			if (i - offset > User.colferSizeMax)
				throw new IllegalStateException(format("colfer: com/example/media.User exceeds %d bytes", User.colferSizeMax));
			if (i > buf.length) throw new BufferOverflowException();
			throw e;
		}
	}

	/**
	 * Deserializes the object.
	 * @param buf the data source.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferUnderflowException when {@code buf} is incomplete. (EOF)
	 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 * @throws InputMismatchException when the data does not match this object's schema.
	 */
	public int unmarshal(byte[] buf, int offset) {
		return unmarshal(buf, offset, buf.length);
	}

	/**
	 * Deserializes the object.
	 * @param buf the data source.
	 * @param offset the initial index for {@code buf}, inclusive.
	 * @param end the index limit for {@code buf}, exclusive.
	 * @return the final index for {@code buf}, exclusive.
	 * @throws BufferUnderflowException when {@code buf} is incomplete. (EOF)
	 * @throws SecurityException on an upper limit breach defined by either {@link #colferSizeMax} or {@link #colferListMax}.
	 * @throws InputMismatchException when the data does not match this object's schema.
	 */
	public int unmarshal(byte[] buf, int offset, int end) {
		if (end > buf.length) end = buf.length;
		int i = offset;

		try {
			byte header = buf[i++];

			if (header == (byte) 0) {
				long x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					if (shift == 56 || b >= 0) {
						x |= (b & 0xffL) << shift;
						break;
					}
					x |= (b & 0x7fL) << shift;
				}
				this.id = x;
				header = buf[i++];
			} else if (header == (byte) (0 | 0x80)) {
				long x = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					if (shift == 56 || b >= 0) {
						x |= (b & 0xffL) << shift;
						break;
					}
					x |= (b & 0x7fL) << shift;
				}
				this.id = -x;
				header = buf[i++];
			}

			if (header == (byte) 1) {
				int size = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					size |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (size < 0 || size > User.colferSizeMax)
					throw new SecurityException(format("colfer: com/example/media.User.name size %d exceeds %d UTF-8 bytes", size, User.colferSizeMax));

				int start = i;
				i += size;
				this.name = new String(buf, start, size, StandardCharsets.UTF_8);
				header = buf[i++];
			}

			if (header == (byte) 2) {
				int size = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					size |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (size < 0 || size > User.colferSizeMax)
					throw new SecurityException(format("colfer: com/example/media.User.signature size %d exceeds %d bytes", size, User.colferSizeMax));

				this.signature = new byte[size];
				int start = i;
				i += size;
				System.arraycopy(buf, start, this.signature, 0, size);

				header = buf[i++];
			}

			if (header == (byte) 3) {
				int length = 0;
				for (int shift = 0; true; shift += 7) {
					byte b = buf[i++];
					length |= (b & 0x7f) << shift;
					if (shift == 28 || b >= 0) break;
				}
				if (length < 0 || length > User.colferListMax)
					throw new SecurityException(format("colfer: com/example/media.User.addresses length %d exceeds %d elements", length, User.colferListMax));

				Address[] a = new Address[length];
				for (int ai = 0; ai < length; ai++) {
					Address o = new Address();
					i = o.unmarshal(buf, i, end);
					a[ai] = o;
				}
				this.addresses = a;
				header = buf[i++];
			}

			if (header != (byte) 0x7f)
				throw new InputMismatchException(format("colfer: unknown header at byte %d", i - 1));
		} finally {
			if (i > end && end - offset < User.colferSizeMax) throw new BufferUnderflowException();
			if (i < 0 || i - offset > User.colferSizeMax)
				throw new SecurityException(format("colfer: com/example/media.User exceeds %d bytes", User.colferSizeMax));
			if (i > end) throw new BufferUnderflowException();
		}

		return i;
	}

	// {@link Serializable} version number.
	private static final long serialVersionUID = 4L;

	// {@link Serializable} Colfer extension.
	private void writeObject(ObjectOutputStream out) throws IOException {
		// TODO: better size estimation
		byte[] buf = new byte[1024];
		int n;
		while (true) try {
			n = marshal(buf, 0);
			break;
		} catch (BufferUnderflowException e) {
			buf = new byte[4 * buf.length];
		}

		out.writeInt(n);
		out.write(buf, 0, n);
	}

	// {@link Serializable} Colfer extension.
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		init();

		int n = in.readInt();
		byte[] buf = new byte[n];
		in.readFully(buf);
		unmarshal(buf, 0);
	}

	// {@link Serializable} Colfer extension.
	private void readObjectNoData() throws ObjectStreamException {
		init();
	}

	/**
	 * Gets com/example/media.User.id.
	 * @return the value.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets com/example/media.User.id.
	 * @param value the replacement.
	 */
	public void setId(long value) {
		this.id = value;
	}

	/**
	 * Sets com/example/media.User.id.
	 * @param value the replacement.
	 * @return {link this}.
	 */
	public User withId(long value) {
		this.id = value;
		return this;
	}

	/**
	 * Gets com/example/media.User.name.
	 * @return the value.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets com/example/media.User.name.
	 * @param value the replacement.
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Sets com/example/media.User.name.
	 * @param value the replacement.
	 * @return {link this}.
	 */
	public User withName(String value) {
		this.name = value;
		return this;
	}

	/**
	 * Gets com/example/media.User.signature.
	 * @return the value.
	 */
	public byte[] getSignature() {
		return this.signature;
	}

	/**
	 * Sets com/example/media.User.signature.
	 * @param value the replacement.
	 */
	public void setSignature(byte[] value) {
		this.signature = value;
	}

	/**
	 * Sets com/example/media.User.signature.
	 * @param value the replacement.
	 * @return {link this}.
	 */
	public User withSignature(byte[] value) {
		this.signature = value;
		return this;
	}

	/**
	 * Gets com/example/media.User.addresses.
	 * @return the value.
	 */
	public Address[] getAddresses() {
		return this.addresses;
	}

	/**
	 * Sets com/example/media.User.addresses.
	 * @param value the replacement.
	 */
	public void setAddresses(Address[] value) {
		this.addresses = value;
	}

	/**
	 * Sets com/example/media.User.addresses.
	 * @param value the replacement.
	 * @return {link this}.
	 */
	public User withAddresses(Address[] value) {
		this.addresses = value;
		return this;
	}

	@Override
	public final int hashCode() {
		int h = 1;
		h = 31 * h + (int)(this.id ^ this.id >>> 32);
		if (this.name != null) h = 31 * h + this.name.hashCode();
		for (byte b : this.signature) h = 31 * h + b;
		for (Address o : this.addresses) h = 31 * h + (o == null ? 0 : o.hashCode());
		return h;
	}

	@Override
	public final boolean equals(Object o) {
		return o instanceof User && equals((User) o);
	}

	public final boolean equals(User o) {
		if (o == null) return false;
		if (o == this) return true;
		return o.getClass() == User.class
			&& this.id == o.id
			&& (this.name == null ? o.name == null : this.name.equals(o.name))
			&& java.util.Arrays.equals(this.signature, o.signature)
			&& java.util.Arrays.equals(this.addresses, o.addresses);
	}

}
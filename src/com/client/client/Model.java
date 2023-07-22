package com.client.client;

import com.client.client.cache.definitions.ItemDefinition;
import com.client.client.particles.Particle;
import com.client.client.particles.ParticleAttachment;
import com.client.client.particles.ParticleDefinition;
import com.client.client.particles.ParticleVector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Model extends Animable {

    private static int MAX_POLYGON = 75000;

    public Model() {
    }

    public void filterTriangles() {
        for (int triangleId = 0; triangleId < triangleCount; triangleId++) {
            int l = faceIndicesA[triangleId];
            int k1 = faceIndicesB[triangleId];
            int j2_ = faceIndicesC[triangleId];
            boolean b = true;
            label2:
            for (int triId = 0; triId < triangleCount; triId++) {
                if (triId == triangleId)
                    continue label2;
                if (faceIndicesA[triId] == l) {
                    b = false;
                    break label2;
                }
                if (faceIndicesB[triId] == k1) {
                    b = false;
                    break label2;
                }
                if (faceIndicesC[triId] == j2_) {
                    b = false;
                    break label2;
                }
            }
            if (b) {
                if (triangleInfo != null)
                    // face_render_type[triangleId] = -1;
                    triangleAlpha[triangleId] = 255;

            }
        }
    }

    public static void nullLoader() {
        modelHeader = null;
        hasAnEdgeToRestrict = null;
        outOfReach = null;
        projected_vertex_y = null;
        vertexPerspectiveDepth = null;
        camera_vertex_y = null;
        camera_vertex_x = null;
        camera_vertex_z = null;
        depthListIndices = null;
        faceLists = null;
        anIntArray1673 = null;
        anIntArrayArray1674 = null;
        anIntArray1675 = null;
        anIntArray1676 = null;
        anIntArray1677 = null;
        SINE = null;
        COSINE = null;
        hsl2rgb = null;
        lightDecay = null;
    }

    private static int[] OFFSETS_512_334 = null;
    private static int[] OFFSETS_765_503 = null;

    public static int[] getOffsets(int j, int k) {
        if (j == 512 && k == 334 && OFFSETS_512_334 != null)
            return OFFSETS_512_334;

        if (j == 765 + 1 && k == 503 && OFFSETS_765_503 != null)
            return OFFSETS_765_503;

        int[] t = new int[k];
        for (int l = 0; l < k; l++)
            t[l] = j * l;

        if (j == 512 && k == 334)
            OFFSETS_512_334 = t;

        if (j == 765 + 1 && k == 503)
            OFFSETS_765_503 = t;

        return t;
    }

    public void read525Model(byte abyte0[], int modelID) {
        Stream nc1 = new Stream(abyte0);
        Stream nc2 = new Stream(abyte0);
        Stream nc3 = new Stream(abyte0);
        Stream nc4 = new Stream(abyte0);
        Stream nc5 = new Stream(abyte0);
        Stream nc6 = new Stream(abyte0);
        Stream nc7 = new Stream(abyte0);
        nc1.pos = abyte0.length - 23;
        int numVertices = nc1.readUnsignedWord();
        int numTriangles = nc1.readUnsignedWord();
        int numTexTriangles = nc1.readUnsignedByte();
        ModelHeader ModelDef_1 = modelHeader[modelID] = new ModelHeader();
        ModelDef_1.modelData = abyte0;
        ModelDef_1.verticeCount = numVertices;
        ModelDef_1.triangleCount = numTriangles;
        ModelDef_1.texturedTriangleCount = numTexTriangles;
        int l1 = nc1.readUnsignedByte();
        boolean bool = (0x1 & l1 ^ 0xffffffff) == -2;
        boolean bool_78_ = (l1 & 0x2 ^ 0xffffffff) == -3;
        int i2 = nc1.readUnsignedByte();
        int j2 = nc1.readUnsignedByte();
        int k2 = nc1.readUnsignedByte();
        int l2 = nc1.readUnsignedByte();
        int i3 = nc1.readUnsignedByte();
        int j3 = nc1.readUnsignedWord();
        int k3 = nc1.readUnsignedWord();
        int l3 = nc1.readUnsignedWord();
        int i4 = nc1.readUnsignedWord();
        int j4 = nc1.readUnsignedWord();
        int k4 = 0;
        int l4 = 0;
        int i5 = 0;
        int v = 0;
        int hb = 0;
        int P = 0;
        byte G = 0;
        byte[] x = null;
        byte[] O = null;
        byte[] J = null;
        byte[] F = null;
        byte[] cb = null;
        byte[] gb = null;
        byte[] lb = null;
        int[] ab = null;
        int[] kb = null;
        int[] y = null;
        int[] N = null;
        short[] D = null;
        int[] triangleColours2 = new int[numTriangles];
        if (numTexTriangles > 0) {
            O = new byte[numTexTriangles];
            nc1.pos = 0;
            for (int j5 = 0; j5 < numTexTriangles; j5++) {
                byte byte0 = O[j5] = nc1.readSignedByte();
                if (byte0 == 0)
                    k4++;
                if (byte0 >= 1 && byte0 <= 3)
                    l4++;
                if (byte0 == 2)
                    i5++;
            }
        }
        int k5 = numTexTriangles;
        int l5 = k5;
        k5 += numVertices;
        int i6 = k5;
        if (l1 == 1)
            k5 += numTriangles;
        int j6 = k5;
        k5 += numTriangles;
        int k6 = k5;
        if (i2 == 255)
            k5 += numTriangles;
        int l6 = k5;
        if (k2 == 1)
            k5 += numTriangles;
        int i7 = k5;
        if (i3 == 1)
            k5 += numVertices;
        int j7 = k5;
        if (j2 == 1)
            k5 += numTriangles;
        int k7 = k5;
        k5 += i4;
        int l7 = k5;
        if (l2 == 1)
            k5 += numTriangles * 2;
        int i8 = k5;
        k5 += j4;
        int j8 = k5;
        k5 += numTriangles * 2;
        int k8 = k5;
        k5 += j3;
        int l8 = k5;
        k5 += k3;
        int i9 = k5;
        k5 += l3;
        int j9 = k5;
        k5 += k4 * 6;
        int k9 = k5;
        k5 += l4 * 6;
        int l9 = k5;
        k5 += l4 * 6;
        int i10 = k5;
        k5 += l4;
        int j10 = k5;
        k5 += l4;
        int k10 = k5;
        k5 += l4 + i5 * 2;
        v = numVertices;
        hb = numTriangles;
        P = numTexTriangles;
        int[] vertexX = new int[numVertices];
        int[] vertexY = new int[numVertices];
        int[] vertexZ = new int[numVertices];
        int[] facePoint1 = new int[numTriangles];
        int[] facePoint2 = new int[numTriangles];
        int[] facePoint3 = new int[numTriangles];
        verticesParticle = new int[numVertices];
        vertexLabels = new int[numVertices];
        triangleInfo = new int[numTriangles];
        facePriorities = new int[numTriangles];
        trianglePriorities = new int[numTriangles];
        triangleAlpha = new int[numTriangles];
        triangleLabels = new int[numTriangles];
        if (i3 == 1)
            vertexLabels = new int[numVertices];
        if (bool)
            triangleInfo = new int[numTriangles];
        if (i2 == 255)
            facePriorities = new int[numTriangles];
        else
            G = (byte) i2;
        if (j2 == 1)
            triangleAlpha = new int[numTriangles];
        if (k2 == 1)
            triangleLabels = new int[numTriangles];
        if (l2 == 1)
            D = new short[numTriangles];
        if (l2 == 1 && numTexTriangles > 0)
            x = new byte[numTriangles];
        triangleColours2 = new int[numTriangles];
        int i_115_ = k5;
        int[] texTrianglesPoint1 = null;
        int[] texTrianglesPoint2 = null;
        int[] texTrianglesPoint3 = null;
        if (numTexTriangles > 0) {
            texTrianglesPoint1 = new int[numTexTriangles];
            texTrianglesPoint2 = new int[numTexTriangles];
            texTrianglesPoint3 = new int[numTexTriangles];
            if (l4 > 0) {
                kb = new int[l4];
                N = new int[l4];
                y = new int[l4];
                gb = new byte[l4];
                lb = new byte[l4];
                F = new byte[l4];
            }
            if (i5 > 0) {
                cb = new byte[i5];
                J = new byte[i5];
            }
        }
        nc1.pos = l5;
        nc2.pos = k8;
        nc3.pos = l8;
        nc4.pos = i9;
        nc5.pos = i7;
        int l10 = 0;
        int i11 = 0;
        int j11 = 0;
        for (int k11 = 0; k11 < numVertices; k11++) {
            int l11 = nc1.readUnsignedByte();
            int j12 = 0;
            if ((l11 & 1) != 0)
                j12 = nc2.getSignedSmart();
            int l12 = 0;
            if ((l11 & 2) != 0)
                l12 = nc3.getSignedSmart();
            int j13 = 0;
            if ((l11 & 4) != 0)
                j13 = nc4.getSignedSmart();
            vertexX[k11] = l10 + j12;
            vertexY[k11] = i11 + l12;
            vertexZ[k11] = j11 + j13;
            l10 = vertexX[k11];
            i11 = vertexY[k11];
            j11 = vertexZ[k11];
            if (vertexLabels != null)
                vertexLabels[k11] = nc5.readUnsignedByte();
        }
        nc1.pos = j8;
        nc2.pos = i6;
        nc3.pos = k6;
        nc4.pos = j7;
        nc5.pos = l6;
        nc6.pos = l7;
        nc7.pos = i8;
        for (int i12 = 0; i12 < numTriangles; i12++) {
            triangleColours2[i12] = nc1.readUnsignedWord();
            if (l1 == 1) {
                triangleInfo[i12] = nc2.readSignedByte();
                if (triangleInfo[i12] == 2)
                    triangleColours2[i12] = 65535;
                triangleInfo[i12] = 0;
            }
            if (i2 == 255) {
                facePriorities[i12] = nc3.readSignedByte();
            }
            if (j2 == 1) {
                triangleAlpha[i12] = nc4.readSignedByte();
                if (triangleAlpha[i12] < 0)
                    triangleAlpha[i12] = (256 + triangleAlpha[i12]);
            }
            if (k2 == 1)
                triangleLabels[i12] = nc5.readUnsignedByte();
            if (l2 == 1)
                D[i12] = (short) (nc6.readUnsignedWord() - 1);
            if (x != null)
                if (D[i12] != -1)
                    x[i12] = (byte) (nc7.readUnsignedByte() - 1);
                else
                    x[i12] = -1;
        }
        nc1.pos = k7;
        nc2.pos = j6;
        int k12 = 0;
        int i13 = 0;
        int k13 = 0;
        int l13 = 0;
        for (int i14 = 0; i14 < numTriangles; i14++) {
            int j14 = nc2.readUnsignedByte();
            if (j14 == 1) {
                k12 = nc1.getSignedSmart() + l13;
                l13 = k12;
                i13 = nc1.getSignedSmart() + l13;
                l13 = i13;
                k13 = nc1.getSignedSmart() + l13;
                l13 = k13;
                facePoint1[i14] = k12;
                facePoint2[i14] = i13;
                facePoint3[i14] = k13;
            }
            if (j14 == 2) {
                i13 = k13;
                k13 = nc1.getSignedSmart() + l13;
                l13 = k13;
                facePoint1[i14] = k12;
                facePoint2[i14] = i13;
                facePoint3[i14] = k13;
            }
            if (j14 == 3) {
                k12 = k13;
                k13 = nc1.getSignedSmart() + l13;
                l13 = k13;
                facePoint1[i14] = k12;
                facePoint2[i14] = i13;
                facePoint3[i14] = k13;
            }
            if (j14 == 4) {
                int l14 = k12;
                k12 = i13;
                i13 = l14;
                k13 = nc1.getSignedSmart() + l13;
                l13 = k13;
                facePoint1[i14] = k12;
                facePoint2[i14] = i13;
                facePoint3[i14] = k13;
            }
        }
        nc1.pos = j9;
        nc2.pos = k9;
        nc3.pos = l9;
        nc4.pos = i10;
        nc5.pos = j10;
        nc6.pos = k10;
        for (int k14 = 0; k14 < numTexTriangles; k14++) {
            int i15 = O[k14] & 0xff;
            if (i15 == 0) {
                texTrianglesPoint1[k14] = nc1.readUnsignedWord();
                texTrianglesPoint2[k14] = nc1.readUnsignedWord();
                texTrianglesPoint3[k14] = nc1.readUnsignedWord();
            }
            if (i15 == 1) {
                texTrianglesPoint1[k14] = nc2.readUnsignedWord();
                texTrianglesPoint2[k14] = nc2.readUnsignedWord();
                texTrianglesPoint3[k14] = nc2.readUnsignedWord();
                kb[k14] = nc3.readUnsignedWord();
                N[k14] = nc3.readUnsignedWord();
                y[k14] = nc3.readUnsignedWord();
                gb[k14] = nc4.readSignedByte();
                lb[k14] = nc5.readSignedByte();
                F[k14] = nc6.readSignedByte();
            }
            if (i15 == 2) {
                texTrianglesPoint1[k14] = nc2.readUnsignedWord();
                texTrianglesPoint2[k14] = nc2.readUnsignedWord();
                texTrianglesPoint3[k14] = nc2.readUnsignedWord();
                kb[k14] = nc3.readUnsignedWord();
                N[k14] = nc3.readUnsignedWord();
                y[k14] = nc3.readUnsignedWord();
                gb[k14] = nc4.readSignedByte();
                lb[k14] = nc5.readSignedByte();
                F[k14] = nc6.readSignedByte();
                cb[k14] = nc6.readSignedByte();
                J[k14] = nc6.readSignedByte();
            }
            if (i15 == 3) {
                texTrianglesPoint1[k14] = nc2.readUnsignedWord();
                texTrianglesPoint2[k14] = nc2.readUnsignedWord();
                texTrianglesPoint3[k14] = nc2.readUnsignedWord();
                kb[k14] = nc3.readUnsignedWord();
                N[k14] = nc3.readUnsignedWord();
                y[k14] = nc3.readUnsignedWord();
                gb[k14] = nc4.readSignedByte();
                lb[k14] = nc5.readSignedByte();
                F[k14] = nc6.readSignedByte();
            }
        }
        if (i2 != 255) {
            for (int i12 = 0; i12 < numTriangles; i12++)
                facePriorities[i12] = i2;
        }
        triangleColors = triangleColours2;
        vertexCount = numVertices;
        triangleCount = numTriangles;
        verticesXCoordinate = vertexX;
        verticesYCoordinate = vertexY;
        verticesZCoordinate = vertexZ;
        faceIndicesA = facePoint1;
        faceIndicesB = facePoint2;
        faceIndicesC = facePoint3;
        filterTriangles();
    }

    private int modelId;

    public int getModelId() {
        return modelId;
    }

    public void convertTexturesToOldFormat() {

   /*     if(modelId == 9638) {
            System.out.println(Arrays.toString(faceMaterial));
            System.out.println(Arrays.toString(faceTexture));
            System.out.println(Arrays.toString(triangleColors));
            System.out.println(Arrays.toString(triangleInfo));
        }
*/
        if (faceMaterial == null || faceTexture == null) {
            return;
        }

        for (short tex : faceMaterial) {
            if (tex > Rasterizer.textureAmount) {
                return;
            }
        }

        if (triangleInfo == null) {
            triangleInfo = new int[triangleCount];
        }

        for (int i = 0; i < triangleCount; i++) {
            if (faceMaterial[i] != -1 && faceTexture[i] >= 0) {
                int mask = 2 + (faceTexture[i] << 2);
                triangleInfo[i] = mask;
                triangleColors[i] = faceMaterial[i];
            } else {
                triangleInfo[i] = 0;
            }
        }

      /* System.out.println("Ended up with:");
        System.out.println(Arrays.toString(faceMaterial));
        System.out.println(Arrays.toString(faceTexture));
        System.out.println(Arrays.toString(triangleInfo));
        System.out.println(Arrays.toString(triangleColors));
        System.out.println(Arrays.toString(textureVertexA));
        System.out.println(Arrays.toString(textureVertexB));
        System.out.println(Arrays.toString(textureVertexC));
        System.out.println("------------------------------------------------------");*/
    }

    private static Set<Integer> newTexturedModels = new HashSet<>();

    static {
        addAll(9631, 9638);
        addAll(64837, 64838);
        addAll(50304, 42059);
        addAll(41587, 50620);
        addAll(50598, 50599);
        addAll(50600, 50602);
        addAll(50601, 50603);
        addAll(50604, 60605);
        addAll(50606, 50606);
        addAll(50611, 50612);
        addAll(50622, 50622);
        addAll(50625, 50625);
        addAll(50629, 50629);
        addAll(50643, 50631);
        addAll(50648, 50633);
        addAll(50644, 50635);
        addAll(50636, 50636);
        addAll(50645, 50638);
        addAll(50646, 50640);
        addAll(50647, 50642);
        addAll(50649, 50649);
        addAll(50655, 50650);
        addAll(50656, 50651);
        addAll(50657, 50652);
        addAll(50653, 50658);
        addAll(50654, 50659);
        addAll(50660, 50660);
        addAll(50661, 50661);
        addAll(50623, 50623);
        addAll(50663, 50664);
        addAll(50665, 50666);
        addAll(50667, 50668);
        addAll(50669, 50670);
        addAll(50671, 50671);
        addAll(50674, 50674);
        addAll(50675, 50675);
        addAll(50676, 50676);
        addAll(50677, 50677);
        addAll(50591, 50592);
        addAll(50593, 50594);
        addAll(50595, 50595);
        addAll(50692, 50693);
        addAll(50694, 50695);
        addAll(3944, 3944);
        addAll(22342, 22342);
        addAll(50698, 50698);
        addAll(50701, 50702);
        addAll(50699, 50700);
        addAll(50703, 50703);
        addAll(50704, 50705);
        addAll(50706, 50707);
        addAll(50721, 50721);
        addAll(50708, 50709);
        addAll(50710, 50711);
        addAll(50712, 50713);
        addAll(50714, 50715);
        addAll(50716, 50716);
        addAll(50717, 50718);
        addAll(50719, 50720);
        addAll(50722, 50722);
        addAll(50723, 50724);
        addAll(50725, 50726);
        addAll(50727, 50728);
        addAll(50729, 50730);
        addAll(50731, 50731);
        addAll(41482, 41483);
        addAll(50732,50746);
        addAll(50733,50734);
        addAll(50735,50736);
        addAll(50737,50738);
        addAll(50741,50741);
        addAll(50739,50740);
        addAll(50742,50743);
        addAll(50744,50745);
        addAll(50747, 50747);
        addAll(65382,65382);
        addAll(50748,50749);
        addAll(50750,50751);
        addAll(50752,50753);
        addAll(50754,50755);
        addAll(50756,50757);
        addAll(50758,50758);
        addAll(50761, 50761);
        addAll(50762, 50762);
        addAll(50791, 50798);
        addAll(50792, 50799);
        addAll(50793, 50800);
        addAll(50794, 50801);
        addAll(50795, 50802);
        addAll(50796, 50797);
        addAll(50778, 50779);
        addAll(50780, 50781);
        addAll(50782, 50783);
        addAll(50803, 50804);
        addAll(50805, 50806);
        addAll(50807, 50808);
        addAll(50809, 50810);
        addAll(50811, 50811);
        addAll(50819, 50819);
        addAll(50858, 50859);
        addAll(50845, 50848);
        addAll(50849, 50850);
        addAll(50851, 50852);
        addAll(50853, 50854);
        addAll(50855, 50856);
        addAll(50857);
        addAll(50835, 50836);
        addAll(50837, 50838);
        addAll(50839, 50840);
        addAll(50841, 50842);
        addAll(50843, 50844);
        addAll(50820, 50821);
        addAll(50822, 50823);
        addAll(50847, 50846);
        addAll(50828, 50827);
        addAll(50823, 50824);
        addAll(50825);
        addAll(50860,50861);
        addAll(50862,50863);
        addAll(50864, 50865);
        addAll(50865, 50866);
        addAll(50867, 50868);
        addAll(50869, 50870);
        addAll(50871, 50880);
        addAll(50881, 50882);
        addAll(50883, 50884);
        addAll(50885, 50886);
        addAll(50887, 50888);
        addAll(50889, 50890);
        addAll(50891, 50892);
        addAll(50893, 50894);
        addAll(50895);
        addAll(50900, 50901);
        addAll(50902);
        addAll(50903);



    }

    private static void addAll(int... ids) {
        for (int id : ids) {
           newTexturedModels.add(id);
        }
    }

    public Model(int modelId) {
        this.modelId = modelId;
        // what id did u save it as btw 50906

        byte[] data = modelHeader[modelId].modelData;
       System.out.println("Decoding " + modelId);
        if (modelId > 50000 && data[data.length - 1] == -1 && data[data.length - 2] == -1 && data[data.length - 3] == -1) {
            decode317Custom(data, modelId);
        } else if (data[data.length - 1] == -1 && data[data.length - 2] == -1) {
            if(newTexturedModels.contains(modelId)) {
                decodeNew(data, modelId);
            } else {
                read622Model(data, modelId);
            }
        } else {
            readOldModel(modelId); // i respect what they did to the data, but i admit it confuses me lol. moment.
            //System.out.println("Read old model " + modelId);
        }

        verticesParticle = new int[vertexCount];


        if (trianglePriorities != null) {
            for (int j = 0; j < trianglePriorities.length; j++) {
                trianglePriorities[j] = 10;
            }
        }

        if (newmodel[modelId]) {
            prioritiesFix();
            scale2(4);
            recolour(0, 255);
        }
        if (ItemDefinition.priorityModels.contains(modelId)) {
            if (trianglePriorities != null) {
                for (int j = 0; j < trianglePriorities.length; j++) {
                    trianglePriorities[j] = 10;
                }

                int[] newBoots = new int[]{29249, 29254, 29250, 29255, 29252, 29253};
                for (int i : newBoots) {

                    if (modelId == i)
                        for (int j = 0; j < facePriorities.length; j++) {
                            facePriorities[j] = 10;
                        }


                    if (modelId == 48841 || modelId == 65376 || modelId == 3419 || modelId == 65283 || modelId == 17297
                            || modelId == 2575 || modelId == 65324 || modelId == 65327 || modelId == 65325 || modelId == 65326
                            || modelId == 65374 || modelId == 65375 || modelId == 48825 || modelId == 48817 || modelId == 48802
                            || modelId == 48840 || modelId == 45536 || modelId == 38284 || modelId == 45522 || modelId == 45517
                            || modelId == 45514 || modelId == 45490 || modelId == 48790 || modelId == 59583 || modelId == 65413
                            || modelId == 65477 || modelId == 65392 || modelId == 65390 || modelId == 65388 || modelId == 65386
                            || modelId == 65384 || modelId == 65382 || modelId == 65400 || modelId == 3188 || modelId == 3192
                            || modelId == 26632 || modelId == 65464 || modelId == 65466 || modelId == 65480 || modelId == 65478
                            || modelId == 65479 || modelId == 65483 || modelId == 65482 || modelId == 65488 || modelId == 65476
                            || modelId == 65489 || modelId == 65486) {
                        scaleT(32, 32, 32);
                        translate(0, 0, 0);
                    }
                }
                int[] priorityFix = new int[]{
                        65300};
                for (int i : priorityFix) {
                    if (modelId == i) {
                        if (trianglePriorities == null) {
                            trianglePriorities = new int[triangleCount];
                        }
                        for (int j = 0; j < trianglePriorities.length; j++) {
                            trianglePriorities[j] = 1;

                        }
                    }
                }
            }
        }

        int[][] attachments = ParticleAttachment.getAttachments(modelId);

        if (attachments != null) {
            for (int n = 0; n < attachments.length; n++) {
                int[] attach = attachments[n];
                if (attach[0] == -1) {
                    for (int z = 0; z < faceIndicesA.length; ++z) {
                        verticesParticle[faceIndicesA[z]] = attach[1] + 1;
                    }
                } else if (attach[0] == -2) {
                    for (int z = 0; z < faceIndicesB.length; ++z) {
                        verticesParticle[faceIndicesB[z]] = attach[1] + 1;
                    }
                } else if (attach[0] == -3) {
                    for (int z = 0; z < faceIndicesC.length; ++z) {
                        verticesParticle[faceIndicesC[z]] = attach[1] + 1;
                    }
                } else if (attach[0] == -4) {
                    for (int z = 0; z < faceIndicesA.length; ++z) {
                        verticesParticle[faceIndicesA[z]] = attach[1] + 1;
                    }
                    for (int z = 0; z < faceIndicesB.length; ++z) {
                        verticesParticle[faceIndicesB[z]] = attach[1] + 1;
                    }
                    for (int z = 0; z < faceIndicesC.length; ++z) {
                        verticesParticle[faceIndicesC[z]] = attach[1] + 1;
                    }
                } else {
                    verticesParticle[attach[0]] = attach[1] + 1;
                }
            }
        }
    }

    private int lastRenderedRotation = 0;
    private int renderAtPointX = 0;
    public int renderAtPointZ = 0;
    public int renderAtPointY = 0;
    public int[] verticesParticle;

    public void prioritiesFix() {
        int priority = 10;
        if (facePriorities != null) {
            for (int face = 0; face < facePriorities.length; face++)
                facePriorities[face] = priority;
        }
    }


    public void scale2(int i) {
        for (int i1 = 0; i1 < vertexCount; i1++) {
            verticesXCoordinate[i1] = verticesXCoordinate[i1] / i;
            verticesYCoordinate[i1] = verticesYCoordinate[i1] / i;
            verticesZCoordinate[i1] = verticesZCoordinate[i1] / i;
        }
    }

    private int version;
    private int modelPriority;

    public void decodeNew(byte[] data, int id) {
        modelId = id;
        Stream first = new Stream(data);
        Stream second = new Stream(data);
        Stream third = new Stream(data);
        Stream fourth = new Stream(data);
        Stream fifth = new Stream(data);
        Stream sixth = new Stream(data);
        Stream seventh = new Stream(data);

        first.pos = data.length - 23;
        vertexCount = first.readUnsignedWord();
        triangleCount = first.readUnsignedWord();
        texturedFaces = first.readUnsignedByte();


        // System.err.println("Vertices: " + vertices + " | Faces: " + faces + " | Texture faces: " + texture_faces);

        int flag = first.readUnsignedByte();//texture flag 00 false, 01+ true
        boolean hasFaceTypes = (flag & 0x1) == 1;
        boolean hasParticleEffects = (flag & 0x2) == 2;
        boolean hasBillboards = (flag & 0x4) == 4;
        boolean hasVersion = (flag & 0x8) == 8;
        if (hasVersion) {
            first.pos -= 7;
            version = first.readUnsignedByte();
            first.pos += 6;
        }

        if (version == 15) {
            newmodel[id] = true;
        }

        int model_priority_opcode = first.readUnsignedByte();
        int model_alpha_opcode = first.readUnsignedByte();
        int model_muscle_opcode = first.readUnsignedByte();
        int model_texture_opcode = first.readUnsignedByte();
        int model_bones_opcode = first.readUnsignedByte();
        //619, 1244, 0, true, false, true, true, true
        int model_vertex_x = first.readUnsignedWord();
        int model_vertex_y = first.readUnsignedWord();
        int model_vertex_z = first.readUnsignedWord();
        int model_vertex_points = first.readUnsignedWord();
        int model_texture_indices = first.readUnsignedWord();
        int texture_id_simple = 0;
        int texture_id_complex = 0;
        int texture_id_cube = 0;
        int face;
        if (texturedFaces > 0) {
            textureMap = new short[texturedFaces];
            first.pos = 0;
            for (face = 0; face < texturedFaces; face++) {
                short opcode = textureMap[face] = first.readSignedByte();
                if (opcode == 0) {
                    texture_id_simple++;
                }
                if (opcode >= 1 && opcode <= 3) {
                    texture_id_complex++;
                }
                if (opcode == 2) {
                    texture_id_cube++;
                }

            }
        }
        int pos = texturedFaces;

        int model_vertex_offset = pos;
        pos += vertexCount;

        int model_render_type_offset = pos;
        if (flag == 1)
            pos += triangleCount;

        int model_face_offset = pos;
        pos += triangleCount;

        int model_face_priorities_offset = pos;
        if (model_priority_opcode == 255)
            pos += triangleCount;

        int model_muscle_offset = pos;
        if (model_muscle_opcode == 1)
            pos += triangleCount;

        int model_bones_offset = pos;
        if (model_bones_opcode == 1)
            pos += vertexCount;

        int model_alpha_offset = pos;
        if (model_alpha_opcode == 1)
            pos += triangleCount;

        int model_points_offset = pos;
        pos += model_vertex_points;

        int model_texture_id = pos;
        if (model_texture_opcode == 1)
            pos += triangleCount * 2;

        int model_texture_coordinate_offset = pos;
        pos += model_texture_indices;

        int model_color_offset = pos;
        pos += triangleCount * 2;

        int model_vertex_x_offset = pos;
        pos += model_vertex_x;

        int model_vertex_y_offset = pos;
        pos += model_vertex_y;

        int model_vertex_z_offset = pos;
        pos += model_vertex_z;

        int model_simple_texture_offset = pos;
        pos += texture_id_simple * 6;

        int model_complex_texture_offset = pos;
        pos += texture_id_complex * 6;

        int model_texture_scale_offset = pos;
        pos += texture_id_complex * 6;

        int model_texture_rotation_offset = pos;
        pos += texture_id_complex * 2;

        int model_texture_direction_offset = pos;
        pos += texture_id_complex;

        int model_texture_translate_offset = pos;
        pos += texture_id_complex * 2 + texture_id_cube * 2;

        verticesParticle = new int[vertexCount];
        verticesXCoordinate = new int[vertexCount];
        verticesYCoordinate = new int[vertexCount];
        verticesZCoordinate = new int[vertexCount];
        faceIndicesA = new int[triangleCount];
        faceIndicesB = new int[triangleCount];
        faceIndicesC = new int[triangleCount];
        if (model_bones_opcode == 1)
            vertexLabels = new int[vertexCount];

        if (flag == 1)
            triangleInfo = new int[triangleCount];

        if (model_priority_opcode == 255)
            trianglePriorities = new int[triangleCount];
        else
            modelPriority = (byte) model_priority_opcode;

        if (model_alpha_opcode == 1)
            triangleAlpha = new int[triangleCount];

        if (model_muscle_opcode == 1)
            triangleLabels = new int[triangleCount];

        if (model_texture_opcode == 1)
            faceMaterial = new short[triangleCount];

        if (model_texture_opcode == 1 && texturedFaces > 0)
            faceTexture = new short[triangleCount];

        triangleColors = new int[triangleCount];
        if (texturedFaces > 0) {
            textureVertexA = new int[texturedFaces];
            textureVertexB = new int[texturedFaces];
            textureVertexC = new int[texturedFaces];
        }
        first.pos = model_vertex_offset;
        second.pos = model_vertex_x_offset;
        third.pos = model_vertex_y_offset;
        fourth.pos = model_vertex_z_offset;
        fifth.pos = model_bones_offset;
        int start_x = 0;
        int start_y = 0;

        int start_z = 0;
        for (int point = 0; point < vertexCount; point++) {
            int position_mask = first.readUnsignedByte();
            int x = 0;
            if ((position_mask & 1) != 0) {
                x = second.getSignedSmart();
            }
            int y = 0;
            if ((position_mask & 2) != 0) {
                y = third.getSignedSmart();
            }
            int z = 0;
            if ((position_mask & 4) != 0) {
                z = fourth.getSignedSmart();
            }
            verticesXCoordinate[point] = start_x + x;
            verticesYCoordinate[point] = start_y + y;
            verticesZCoordinate[point] = start_z + z;
            start_x = verticesXCoordinate[point];
            start_y = verticesYCoordinate[point];
            start_z = verticesZCoordinate[point];
            if (vertexLabels != null)
                vertexLabels[point] = fifth.readUnsignedByte();

        }
        first.pos = model_color_offset;
        second.pos = model_render_type_offset;
        third.pos = model_face_priorities_offset;
        fourth.pos = model_alpha_offset;
        fifth.pos = model_muscle_offset;
        sixth.pos = model_texture_id;
        seventh.pos = model_texture_coordinate_offset;
        for (face = 0; face < triangleCount; face++) {
            triangleColors[face] = (short) (first.readUnsignedWord() & 0xFFFF);
            //   System.out.println("Read face color: " + triangleColors[face]);
            if (flag == 1) {
                triangleInfo[face] = second.readSignedByte();
            }
            if (model_priority_opcode == 255) {
                trianglePriorities[face] = third.readSignedByte();
            }
            if (model_alpha_opcode == 1) {
                triangleAlpha[face] = fourth.readSignedByte();
                if (triangleAlpha[face] < 0)
                    triangleAlpha[face] = (256 + triangleAlpha[face]);

            }
            if (model_muscle_opcode == 1)
                triangleLabels[face] = fifth.readUnsignedByte();

            if (model_texture_opcode == 1) {
                //System.out.println("Started reading face material at pos " + sixth.pos);
                faceMaterial[face] = (short) (sixth.readUnsignedWord() - 1);
                if (faceMaterial[face] >= 0) {
                    if (triangleInfo != null) {
                        if (triangleInfo[face] < 2
                                && triangleColors[face] != 127
                                && triangleColors[face] != -27075
                                && triangleColors[face] != 8128
                                && triangleColors[face] != 7510) {
                            faceMaterial[face] = -1;
                        }
                    }
                }


                if (faceMaterial[face] != -1 && faceMaterial[face] >= 0 && faceMaterial[face] <= Rasterizer.textureAmount)
                    triangleColors[face] = 127;

            }

            //System.out.println(Arrays.toString(triangleColors));
            if (faceTexture != null && faceMaterial[face] != -1) {
                faceTexture[face] = (byte) (seventh.readUnsignedByte() - 1);
            }
        }
        first.pos = model_points_offset;
        second.pos = model_face_offset;
        int a = 0;
        int b = 0;
        int c = 0;
        int last_coordinate = 0;
        for (face = 0; face < triangleCount; face++) {
            int opcode = second.readUnsignedByte();
            if (opcode == 1) {
                a = first.getSignedSmart() + last_coordinate;
                last_coordinate = a;
                b = first.getSignedSmart() + last_coordinate;
                last_coordinate = b;
                c = first.getSignedSmart() + last_coordinate;
                last_coordinate = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
            if (opcode == 2) {
                b = c;
                c = first.getSignedSmart() + last_coordinate;
                last_coordinate = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
            if (opcode == 3) {
                a = c;
                c = first.getSignedSmart() + last_coordinate;
                last_coordinate = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
            if (opcode == 4) {
                int l14 = a;
                a = b;
                b = l14;
                c = first.getSignedSmart() + last_coordinate;
                last_coordinate = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
        }
        first.pos = model_simple_texture_offset;
        second.pos = model_complex_texture_offset;
        third.pos = model_texture_scale_offset;
        fourth.pos = model_texture_rotation_offset;
        fifth.pos = model_texture_direction_offset;
        sixth.pos = model_texture_translate_offset;
        for (face = 0; face < texturedFaces; face++) {
            int opcode = textureMap[face] & 0xff;
            if (opcode == 0) {
                textureVertexA[face] = (short) first.readUnsignedWord();
                textureVertexB[face] = (short) first.readUnsignedWord();
                textureVertexC[face] = (short) first.readUnsignedWord();
            }
            if (opcode == 1) {
                textureVertexA[face] = (short) second.readUnsignedWord();
                textureVertexB[face] = (short) second.readUnsignedWord();
                textureVertexC[face] = (short) second.readUnsignedWord();
            }
            if (opcode == 2) {
                textureVertexA[face] = (short) second.readUnsignedWord();
                textureVertexB[face] = (short) second.readUnsignedWord();
                textureVertexC[face] = (short) second.readUnsignedWord();
            }
            if (opcode == 3) {
                textureVertexA[face] = (short) second.readUnsignedWord();
                textureVertexB[face] = (short) second.readUnsignedWord();
                textureVertexC[face] = (short) second.readUnsignedWord();
            }
        }

        //first.pos = pos;
       /* face = first.readUnsignedByte();
        if (face != 0) {
            first.readUnsignedWord();
            first.readUnsignedWord();
            first.readUnsignedWord();
            first.getInt();
        }*/
        convertTexturesToOldFormat();
    }

    public void read622Model(byte data[], int modelID) {
        this.modelId = modelID;
        Stream first = new Stream(data);
        Stream second = new Stream(data);
        Stream third = new Stream(data);
        Stream fourth = new Stream(data);
        Stream fifth = new Stream(data);
        Stream sixth = new Stream(data);
        Stream seventh = new Stream(data);
        first.pos = data.length - 23;
        int numVertices = first.readUnsignedWord();
        int numTriangles = first.readUnsignedWord();
        int texturedFaces = first.readUnsignedByte();
        ModelHeader ModelDef_1 = modelHeader[modelID] = new ModelHeader();
        ModelDef_1.modelData = data;
        ModelDef_1.verticeCount = numVertices;
        ModelDef_1.triangleCount = numTriangles;
        ModelDef_1.texturedTriangleCount = texturedFaces;
        int flag = first.readUnsignedByte();
        boolean bool = (0x1 & flag ^ 0xffffffff) == -2;
        boolean bool_78_ = (flag & 0x2 ^ 0xffffffff) == -3;
        boolean bool_25_ = (0x4 & flag) == 4;
        boolean bool_26_ = (0x8 & flag) == 8;
        if (!bool_26_) {
            read525Model(data, modelID);
            return;
        }
        int newformat = 0;
        if (bool_26_) {
            first.pos -= 7;
            newformat = first.readUnsignedByte();
            first.pos += 6;
        }
        if (newformat == 15) {
            newmodel[modelID] = true;
        }

        int i2 = first.readUnsignedByte();
        int j2 = first.readUnsignedByte();
        int k2 = first.readUnsignedByte();
        int l2 = first.readUnsignedByte();
        int i3 = first.readUnsignedByte();
        int j3 = first.readUnsignedWord();
        int k3 = first.readUnsignedWord();
        int l3 = first.readUnsignedWord();
        int i4 = first.readUnsignedWord();
        int j4 = first.readUnsignedWord();
        int k4 = 0;
        int l4 = 0;
        int i5 = 0;
        int v = 0;
        int hb = 0;
        int P = 0;
        byte G = 0;
        short[] x = null;
        byte[] J = null;
        byte[] F = null;
        byte[] cb = null;
        byte[] gb = null;
        byte[] lb = null;
        int[] ab = null;
        int[] kb = null;
        int[] y = null;
        int[] N = null;
        short[] D = null;
        int[] triangleColours2;
        if (texturedFaces > 0) {
            textureMap = new short[texturedFaces];
            first.pos = 0;
            for (int j5 = 0; j5 < texturedFaces; j5++) {
                short opcode = textureMap[j5] = first.readSignedByte();
                if (opcode == 0)
                    k4++;
                if (opcode >= 1 && opcode <= 3)
                    l4++;
                if (opcode == 2)
                    i5++;
            }
        }
        int k5 = texturedFaces;
        int l5 = k5;
        k5 += numVertices;
        int i6 = k5;
        if (bool)
            k5 += numTriangles;
        if (flag == 1)
            k5 += numTriangles;
        int j6 = k5;
        k5 += numTriangles;
        int k6 = k5;
        if (i2 == 255)
            k5 += numTriangles;
        int l6 = k5;
        if (k2 == 1)
            k5 += numTriangles;
        int i7 = k5;
        if (i3 == 1)
            k5 += numVertices;
        int j7 = k5;
        if (j2 == 1)
            k5 += numTriangles;
        int k7 = k5;
        k5 += i4;
        int l7 = k5;
        if (l2 == 1)
            k5 += numTriangles * 2;
        int i8 = k5;
        k5 += j4;
        int j8 = k5;
        k5 += numTriangles * 2;
        int k8 = k5;
        k5 += j3;
        int l8 = k5;
        k5 += k3;
        int i9 = k5;
        k5 += l3;
        int j9 = k5;
        k5 += k4 * 6;
        int k9 = k5;
        k5 += l4 * 6;
        int i_59_ = 6;
        if (newformat != 14) {
            if (newformat >= 15)
                i_59_ = 9;
        } else
            i_59_ = 7;
        int l9 = k5;
        k5 += i_59_ * l4;
        int i10 = k5;
        k5 += l4;
        int j10 = k5;
        k5 += l4;
        int k10 = k5;
        k5 += l4 + i5 * 2;
        v = numVertices;
        hb = numTriangles;
        P = texturedFaces;
        int[] vertexX = new int[numVertices];
        int[] vertexY = new int[numVertices];
        int[] vertexZ = new int[numVertices];
        int[] facePoint1 = new int[numTriangles];
        int[] facePoint2 = new int[numTriangles];
        int[] facePoint3 = new int[numTriangles];
        verticesParticle = new int[numVertices];
        vertexLabels = new int[numVertices];
        triangleInfo = new int[numTriangles];
        facePriorities = new int[numTriangles];
        trianglePriorities = new int[numTriangles];
        triangleAlpha = new int[numTriangles];
        triangleLabels = new int[numTriangles];
        if (i3 == 1)
            vertexLabels = new int[numVertices];
        if (bool)
            triangleInfo = new int[numTriangles];
        if (i2 == 255)
            facePriorities = new int[numTriangles];
        else
            G = (byte) i2;
        if (j2 == 1)
            triangleAlpha = new int[numTriangles];
        if (k2 == 1)
            triangleLabels = new int[numTriangles];
        if (l2 == 1)
            D = new short[numTriangles];
        if (l2 == 1 && texturedFaces > 0)
            x = new short[numTriangles];
        triangleColours2 = new int[numTriangles];
        int i_115_ = k5;
        int[] texTrianglesPoint1 = null;
        int[] texTrianglesPoint2 = null;
        int[] texTrianglesPoint3 = null;
        if (texturedFaces > 0) {
            texTrianglesPoint1 = new int[texturedFaces];
            texTrianglesPoint2 = new int[texturedFaces];
            texTrianglesPoint3 = new int[texturedFaces];
            if (l4 > 0) {
                kb = new int[l4];
                N = new int[l4];
                y = new int[l4];
                gb = new byte[l4];
                lb = new byte[l4];
                F = new byte[l4];
            }
            if (i5 > 0) {
                cb = new byte[i5];
                J = new byte[i5];
            }
        }
        first.pos = l5;
        second.pos = k8;
        third.pos = l8;
        fourth.pos = i9;
        fifth.pos = i7;
        int l10 = 0;
        int i11 = 0;
        int j11 = 0;
        for (int k11 = 0; k11 < numVertices; k11++) {
            int l11 = first.readUnsignedByte();
            int j12 = 0;
            if ((l11 & 1) != 0)
                j12 = second.getSignedSmart();
            int l12 = 0;
            if ((l11 & 2) != 0)
                l12 = third.getSignedSmart();
            int j13 = 0;
            if ((l11 & 4) != 0)
                j13 = fourth.getSignedSmart();
            vertexX[k11] = l10 + j12;
            vertexY[k11] = i11 + l12;
            vertexZ[k11] = j11 + j13;
            l10 = vertexX[k11];
            i11 = vertexY[k11];
            j11 = vertexZ[k11];
            if (vertexLabels != null)
                vertexLabels[k11] = fifth.readUnsignedByte();
        }
        first.pos = j8;
        second.pos = i6;
        third.pos = k6;
        fourth.pos = j7;
        fifth.pos = l6;
        sixth.pos = l7;
        seventh.pos = i8;
        for (int i12 = 0; i12 < numTriangles; i12++) {
            triangleColours2[i12] = first.readUnsignedWord();
            if (flag == 1) {
                triangleInfo[i12] = second.readSignedByte();
                if (triangleInfo[i12] == 2)
                    triangleColours2[i12] = 65535;
                triangleInfo[i12] = 0;
            }
            if (i2 == 255) {
                facePriorities[i12] = third.readSignedByte();
            }
            if (j2 == 1) {
                triangleAlpha[i12] = fourth.readSignedByte();
                if (triangleAlpha[i12] < 0)
                    triangleAlpha[i12] = (256 + triangleAlpha[i12]);
            }
            if (k2 == 1)
                triangleLabels[i12] = fifth.readUnsignedByte();
            if (l2 == 1)
                D[i12] = (short) (sixth.readUnsignedWord() - 1);
            if (x != null)
                if (D[i12] != -1)
                    x[i12] = (byte) (seventh.readUnsignedByte() - 1);
                else
                    x[i12] = -1;
        }
        faceMaterial = D;
        faceTexture = x;
        first.pos = k7;
        second.pos = j6;
        int point1 = 0;
        int point2 = 0;
        int point3 = 0;
        int offset = 0;
        for (int i14 = 0; i14 < numTriangles; i14++) {
            int triangleType = second.readUnsignedByte();
            if (triangleType == 1) {
                point1 = first.getSignedSmart() + offset;
                offset = point1;
                point2 = first.getSignedSmart() + offset;
                offset = point2;
                point3 = first.getSignedSmart() + offset;
                offset = point3;
                facePoint1[i14] = point1;
                facePoint2[i14] = point2;
                facePoint3[i14] = point3;
            }
            if (triangleType == 2) {
                point2 = point3;
                point3 = first.getSignedSmart() + offset;
                offset = point3;
                facePoint1[i14] = point1;
                facePoint2[i14] = point2;
                facePoint3[i14] = point3;
            }
            if (triangleType == 3) {
                point1 = point3;
                point3 = first.getSignedSmart() + offset;
                offset = point3;
                facePoint1[i14] = point1;
                facePoint2[i14] = point2;
                facePoint3[i14] = point3;
            }
            if (triangleType == 4) {
                int pointOffset = point1;
                point1 = point2;
                point2 = pointOffset;
                point3 = first.getSignedSmart() + offset;
                offset = point3;
                facePoint1[i14] = point1;
                facePoint2[i14] = point2;
                facePoint3[i14] = point3;
            }
        }
        first.pos = j9;
        second.pos = k9;
        third.pos = l9;
        fourth.pos = i10;
        fifth.pos = j10;
        sixth.pos = k10;
        for (int k14 = 0; k14 < texturedFaces; k14++) {
            int i15 = textureMap[k14] & 0xff;
            if (i15 == 0) {
                texTrianglesPoint1[k14] = first.readUnsignedWord();
                texTrianglesPoint2[k14] = first.readUnsignedWord();
                texTrianglesPoint3[k14] = first.readUnsignedWord();
            }
            if (i15 == 1) {
                texTrianglesPoint1[k14] = second.readUnsignedWord();
                texTrianglesPoint2[k14] = second.readUnsignedWord();
                texTrianglesPoint3[k14] = second.readUnsignedWord();
                if (newformat < 15) {
                    kb[k14] = third.readUnsignedWord();
                    if (newformat >= 14)
                        N[k14] = third.v(-1);
                    else
                        N[k14] = third.readUnsignedWord();
                    y[k14] = third.readUnsignedWord();
                } else {
                    kb[k14] = third.v(-1);
                    N[k14] = third.v(-1);
                    y[k14] = third.v(-1);
                }
                gb[k14] = fourth.readSignedByte();
                lb[k14] = fifth.readSignedByte();
                F[k14] = sixth.readSignedByte();
            }
            if (i15 == 2) {
                texTrianglesPoint1[k14] = second.readUnsignedWord();
                texTrianglesPoint2[k14] = second.readUnsignedWord();
                texTrianglesPoint3[k14] = second.readUnsignedWord();
                if (newformat >= 15) {
                    kb[k14] = third.v(-1);
                    N[k14] = third.v(-1);
                    y[k14] = third.v(-1);
                } else {
                    kb[k14] = third.readUnsignedWord();
                    if (newformat < 14)
                        N[k14] = third.readUnsignedWord();
                    else
                        N[k14] = third.v(-1);
                    y[k14] = third.readUnsignedWord();
                }
                gb[k14] = fourth.readSignedByte();
                lb[k14] = fifth.readSignedByte();
                F[k14] = sixth.readSignedByte();
                cb[k14] = sixth.readSignedByte();
                J[k14] = sixth.readSignedByte();
            }
            if (i15 == 3) {
                texTrianglesPoint1[k14] = second.readUnsignedWord();
                texTrianglesPoint2[k14] = second.readUnsignedWord();
                texTrianglesPoint3[k14] = second.readUnsignedWord();
                if (newformat < 15) {
                    kb[k14] = third.readUnsignedWord();
                    if (newformat < 14)
                        N[k14] = third.readUnsignedWord();
                    else
                        N[k14] = third.v(-1);
                    y[k14] = third.readUnsignedWord();
                } else {
                    kb[k14] = third.v(-1);
                    N[k14] = third.v(-1);
                    y[k14] = third.v(-1);
                }
                gb[k14] = fourth.readSignedByte();
                lb[k14] = fifth.readSignedByte();
                F[k14] = sixth.readSignedByte();
            }
        }
        if (i2 != 255) {
            for (int i12 = 0; i12 < numTriangles; i12++)
                facePriorities[i12] = i2;
        }
        triangleColors = triangleColours2;
        vertexCount = numVertices;
        triangleCount = numTriangles;
        verticesXCoordinate = vertexX;
        verticesYCoordinate = vertexY;
        verticesZCoordinate = vertexZ;
        faceIndicesA = facePoint1;
        faceIndicesB = facePoint2;
        faceIndicesC = facePoint3;
        textureVertexA = texTrianglesPoint1;
        textureVertexB = texTrianglesPoint2;
        textureVertexC = texTrianglesPoint3;
        filterTriangles();

        for (int j = 0; j < facePriorities.length; j++) {
            facePriorities[j] = 10;
        }

    }


    public short[] faceMaterial;
    public short[] faceTexture;
    public short[] textureMap;

    // do u have java 15 btw? no do i need to get it? ye sec

    public void decode317Custom(byte[] data, int id) { // kk sent u the editor in discord
        //    System.out.println("Decoded old custom " + id + " | " + data.length);
        modelId = id;
        Stream first = new Stream(data);
        Stream second = new Stream(data);
        Stream third = new Stream(data);
        Stream fourth = new Stream(data);
        Stream fifth = new Stream(data);
        first.pos = data.length - 23;
        vertexCount = first.readUnsignedWord();
        triangleCount = first.readUnsignedWord();
        texturedFaces = first.readUnsignedByte();


        int renderTypeOpcode = first.readUnsignedByte();
        int renderPriorityOpcode = first.readUnsignedByte();
        int triangleAlphaOpcode = first.readUnsignedByte();
        int triangleSkinOpcode = first.readUnsignedByte();
        int vertexLabelOpcode = first.readUnsignedByte();
        int verticesXCoordinateOffset = first.readUnsignedWord();

        int verticesYCoordinateOffset = first.readUnsignedWord();
        int verticesZCoordinateOffset = first.readUnsignedWord();
        int triangleIndicesOffset = first.readUnsignedWord();

        int particleCount = first.readUnsignedWord();

        boolean hasParticles = particleCount > 0;

        int pos = 0;

        int vertexFlagOffset = pos;
        pos += vertexCount;

        int triangleCompressTypeOffset = pos;
        pos += triangleCount;

        int facePriorityOffset = pos;
        if (renderPriorityOpcode == 255) {
            pos += triangleCount;
        }

        int triangleSkinOffset = pos;
        if (triangleSkinOpcode == 1) {
            pos += triangleCount;
        }

        int renderTypeOffset = pos;
        if (renderTypeOpcode == 1) {
            pos += triangleCount;
        }

        int vertexLabelsOffset = pos;
        if (vertexLabelOpcode == 1) {
            pos += vertexCount;
        }

        int triangleAlphaOffset = pos;
        if (triangleAlphaOpcode == 1) {
            pos += triangleCount;
        }

        int indicesOffset = pos;
        pos += triangleIndicesOffset;

        int triangleColorOffset = pos;
        pos += triangleCount * 2;

        int textureOffset = pos;
        pos += texturedFaces * 6;

        int xOffset = pos;
        pos += verticesXCoordinateOffset;

        int yOffset = pos;
        pos += verticesYCoordinateOffset;

        int zOffset = pos;

        verticesParticle = new int[vertexCount];
        verticesXCoordinate = new int[vertexCount];
        verticesYCoordinate = new int[vertexCount];
        verticesZCoordinate = new int[vertexCount];
        faceIndicesA = new int[triangleCount];
        faceIndicesB = new int[triangleCount];
        faceIndicesC = new int[triangleCount];
        if (texturedFaces > 0) {
            textureVertexA = new int[texturedFaces];
            textureVertexB = new int[texturedFaces];
            textureVertexC = new int[texturedFaces];
        }

        if (vertexLabelOpcode == 1)
            vertexLabels = new int[vertexCount];


        if (renderTypeOpcode == 1) {
            triangleInfo = new int[triangleCount];
        }

        if (renderPriorityOpcode == 255)
            trianglePriorities = new int[triangleCount];
        else
            face_priority = (byte) renderPriorityOpcode;

        if (triangleAlphaOpcode == 1)
            triangleAlpha = new int[triangleCount];

        if (triangleSkinOpcode == 1)
            triangleLabels = new int[triangleCount];

        triangleColors = new int[triangleCount];
        first.pos = vertexFlagOffset;
        second.pos = xOffset;
        third.pos = yOffset;
        fourth.pos = zOffset;
        fifth.pos = vertexLabelsOffset; // 18 +
        int baseX = 0;
        int baseY = 0;
        int baseZ = 0;

        for (int point = 0; point < vertexCount; point++) {
            int flag = first.readUnsignedByte();

            int x = 0;
            if ((flag & 0x1) != 0) {
                x = second.getSignedSmart();
            }

            int y = 0;
            if ((flag & 0x2) != 0) {
                y = third.getSignedSmart();
            }
            int z = 0;
            if ((flag & 0x4) != 0) {
                z = fourth.getSignedSmart();
            }

            verticesXCoordinate[point] = baseX + x;
            verticesYCoordinate[point] = baseY + y;
            verticesZCoordinate[point] = baseZ + z;
            baseX = verticesXCoordinate[point];
            baseY = verticesYCoordinate[point];
            baseZ = verticesZCoordinate[point];
            if (vertexLabelOpcode == 1) {
                vertexLabels[point] = fifth.readUnsignedByte();
            }
        }


        first.pos = triangleColorOffset;
        second.pos = renderTypeOffset;
        third.pos = facePriorityOffset;
        fourth.pos = triangleAlphaOffset;
        fifth.pos = triangleSkinOffset;

        for (int face = 0; face < triangleCount; face++) {
            int color = first.readUnsignedWord();
            triangleColors[face] = (short) color;

            if (renderTypeOpcode == 1) {
                triangleInfo[face] = second.readUnsignedByte();
            }
            if (renderPriorityOpcode == 255) {
                trianglePriorities[face] = third.readSignedByte();
            }

            if (triangleAlphaOpcode == 1) {
                triangleAlpha[face] = fourth.readSignedByte();
                if (triangleAlpha[face] < 0) {
                    triangleAlpha[face] = (256 + triangleAlpha[face]);
                }

            }
            if (triangleSkinOpcode == 1) {
                triangleLabels[face] = fifth.readUnsignedByte();
            }

        }
        first.pos = indicesOffset;
        second.pos = triangleCompressTypeOffset;
        int a = 0;
        int b = 0;
        int c = 0;
        int offset = 0;
        int coordinate;

        for (int face = 0; face < triangleCount; face++) {
            int opcode = second.readUnsignedByte();

            if (opcode == 1) {
                a = (first.getSignedSmart() + offset);
                offset = a;
                b = (first.getSignedSmart() + offset);
                offset = b;
                c = (first.getSignedSmart() + offset);
                offset = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;

            }
            if (opcode == 2) {
                b = c;
                c = (first.getSignedSmart() + offset);
                offset = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
            if (opcode == 3) {
                a = c;
                c = (first.getSignedSmart() + offset);
                offset = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }
            if (opcode == 4) {
                coordinate = a;
                a = b;
                b = coordinate;
                c = (first.getSignedSmart() + offset);
                offset = c;
                faceIndicesA[face] = a;
                faceIndicesB[face] = b;
                faceIndicesC[face] = c;
            }

        }
        first.pos = textureOffset;

        for (int face = 0; face < texturedFaces; face++) {
            textureVertexA[face] = (short) first.readUnsignedWord();
            textureVertexB[face] = (short) first.readUnsignedWord();
            textureVertexC[face] = (short) first.readUnsignedWord();
        }
        verticesParticle = new int[vertexCount];
        if (hasParticles) {
            first.pos = (data.length - 23) - (particleCount * 4);
            for (int vertex = 0; vertex < particleCount; vertex++) {
                int index = first.readUnsignedWord();
                int definitionIndex = first.readUnsignedWord();
                verticesParticle[index] = definitionIndex;
            }
        }
        //    System.out.println("Particles len = " + particleCount);

        if (triangleInfo == null) {
            triangleInfo = new int[triangleCount];
        }
        //   System.out.println("Tri info = " + Arrays.toString(triangleInfo));

    }

    private void readOldModel(int i) {
        int j = -870;
        aBoolean1618 = true;
        rendersWithinOneTile = false;
        anInt1620++;
        ModelHeader header = modelHeader[i];
        vertexCount = header.verticeCount;
        triangleCount = header.triangleCount;
        texturedFaces = header.texturedTriangleCount;
        verticesParticle = new int[vertexCount];
        verticesXCoordinate = new int[vertexCount];
        verticesYCoordinate = new int[vertexCount];
        verticesZCoordinate = new int[vertexCount];
        faceIndicesA = new int[triangleCount];
        faceIndicesB = new int[triangleCount];
        while (j >= 0)
            aBoolean1618 = !aBoolean1618;
        faceIndicesC = new int[triangleCount];
        textureVertexA = new int[texturedFaces];
        textureVertexB = new int[texturedFaces];
        textureVertexC = new int[texturedFaces];
        if (header.vskinBasePos >= 0)
            vertexLabels = new int[vertexCount];
        if (header.drawTypeBasePos >= 0)
            triangleInfo = new int[triangleCount];
        if (header.facePriorityBasePos >= 0)
            facePriorities = new int[triangleCount];
        else
            face_priority = -header.facePriorityBasePos - 1;
        if (header.alphaBasepos >= 0)
            triangleAlpha = new int[triangleCount];
        if (header.tskinBasepos >= 0)
            triangleLabels = new int[triangleCount];
        triangleColors = new int[triangleCount];
        Stream stream = new Stream(header.modelData);
        stream.pos = header.verticesModOffset;
        Stream stream_1 = new Stream(header.modelData);
        stream_1.pos = header.verticesXOffset;
        Stream stream_2 = new Stream(header.modelData);
        stream_2.pos = header.verticesYOffset;
        Stream stream_3 = new Stream(header.modelData);
        stream_3.pos = header.verticesZOffset;
        Stream stream_4 = new Stream(header.modelData);
        stream_4.pos = header.vskinBasePos;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < vertexCount; j1++) {
            int k1 = stream.readUnsignedByte();
            int i2 = 0;
            if ((k1 & 1) != 0)
                i2 = stream_1.getSignedSmart();
            int k2 = 0;
            if ((k1 & 2) != 0)
                k2 = stream_2.getSignedSmart();
            int i3 = 0;
            if ((k1 & 4) != 0)
                i3 = stream_3.getSignedSmart();
            verticesXCoordinate[j1] = k + i2;
            verticesYCoordinate[j1] = l + k2;
            verticesZCoordinate[j1] = i1 + i3;
            k = verticesXCoordinate[j1];
            l = verticesYCoordinate[j1];
            i1 = verticesZCoordinate[j1];
            if (vertexLabels != null)
                vertexLabels[j1] = stream_4.readUnsignedByte();
        }
        stream.pos = header.triColourOffset;
        stream_1.pos = header.drawTypeBasePos;
        stream_2.pos = header.facePriorityBasePos;
        stream_3.pos = header.alphaBasepos;
        stream_4.pos = header.tskinBasepos;
        for (int l1 = 0; l1 < triangleCount; l1++) {
            triangleColors[l1] = stream.readUnsignedWord();
            if (triangleInfo != null)
                triangleInfo[l1] = stream_1.readUnsignedByte();
            if (facePriorities != null)
                facePriorities[l1] = stream_2.readUnsignedByte();
            if (triangleAlpha != null) {
                triangleAlpha[l1] = stream_3.readUnsignedByte();
            }
            if (triangleLabels != null)
                triangleLabels[l1] = stream_4.readUnsignedByte();
        }
        stream.pos = header.triVPointOffset;
        stream_1.pos = header.triMeshLinkOffset;
        int j2 = 0;
        int l2 = 0;
        int j3 = 0;
        int k3 = 0;
        for (int l3 = 0; l3 < triangleCount; l3++) {
            int i4 = stream_1.readUnsignedByte();
            if (i4 == 1) {
                j2 = stream.getSignedSmart() + k3;
                k3 = j2;
                l2 = stream.getSignedSmart() + k3;
                k3 = l2;
                j3 = stream.getSignedSmart() + k3;
                k3 = j3;
                faceIndicesA[l3] = j2;
                faceIndicesB[l3] = l2;
                faceIndicesC[l3] = j3;
            }
            if (i4 == 2) {
                j2 = j2;
                l2 = j3;
                j3 = stream.getSignedSmart() + k3;
                k3 = j3;
                faceIndicesA[l3] = j2;
                faceIndicesB[l3] = l2;
                faceIndicesC[l3] = j3;
            }
            if (i4 == 3) {
                j2 = j3;
                l2 = l2;
                j3 = stream.getSignedSmart() + k3;
                k3 = j3;
                faceIndicesA[l3] = j2;
                faceIndicesB[l3] = l2;
                faceIndicesC[l3] = j3;
            }
            if (i4 == 4) {
                int k4 = j2;
                j2 = l2;
                l2 = k4;
                j3 = stream.getSignedSmart() + k3;
                k3 = j3;
                faceIndicesA[l3] = j2;
                faceIndicesB[l3] = l2;
                faceIndicesC[l3] = j3;
            }
        }
        stream.pos = header.textureInfoBasePos;
        for (int j4 = 0; j4 < texturedFaces; j4++) {
            textureVertexA[j4] = stream.readUnsignedWord();
            textureVertexB[j4] = stream.readUnsignedWord();
            textureVertexC[j4] = stream.readUnsignedWord();
        }
        // filterTriangles();
    }

    public static void readFirstModelData(byte abyte0[], int j) {
        try {
            if (abyte0 == null) {
                ModelHeader class21 = modelHeader[j] = new ModelHeader();
                class21.verticeCount = 0;
                class21.triangleCount = 0;
                class21.texturedTriangleCount = 0;
                return;
            }
            Stream stream = new Stream(abyte0);
            stream.pos = abyte0.length - 18;
            ModelHeader class21_1 = modelHeader[j] = new ModelHeader();
            class21_1.modelData = abyte0;
            class21_1.verticeCount = stream.readUnsignedWord();
            class21_1.triangleCount = stream.readUnsignedWord();
            class21_1.texturedTriangleCount = stream.readUnsignedByte();
            int k = stream.readUnsignedByte();
            int l = stream.readUnsignedByte();
            int i1 = stream.readUnsignedByte();
            int j1 = stream.readUnsignedByte();
            int k1 = stream.readUnsignedByte();
            int l1 = stream.readUnsignedWord();
            int i2 = stream.readUnsignedWord();
            int j2 = stream.readUnsignedWord();
            int k2 = stream.readUnsignedWord();
            int l2 = 0;
            class21_1.verticesModOffset = l2;
            l2 += class21_1.verticeCount;
            class21_1.triMeshLinkOffset = l2;
            l2 += class21_1.triangleCount;
            class21_1.facePriorityBasePos = l2;
            if (l == 255)
                l2 += class21_1.triangleCount;
            else
                class21_1.facePriorityBasePos = -l - 1;
            class21_1.tskinBasepos = l2;
            if (j1 == 1)
                l2 += class21_1.triangleCount;
            else
                class21_1.tskinBasepos = -1;
            class21_1.drawTypeBasePos = l2;
            if (k == 1)
                l2 += class21_1.triangleCount;
            else
                class21_1.drawTypeBasePos = -1;
            class21_1.vskinBasePos = l2;
            if (k1 == 1)
                l2 += class21_1.verticeCount;
            else
                class21_1.vskinBasePos = -1;
            class21_1.alphaBasepos = l2;
            if (i1 == 1)
                l2 += class21_1.triangleCount;
            else
                class21_1.alphaBasepos = -1;
            class21_1.triVPointOffset = l2;
            l2 += k2;
            class21_1.triColourOffset = l2;
            l2 += class21_1.triangleCount * 2;
            class21_1.textureInfoBasePos = l2;
            l2 += class21_1.texturedTriangleCount * 6;
            class21_1.verticesXOffset = l2;
            l2 += l1;
            class21_1.verticesYOffset = l2;
            l2 += i2;
            class21_1.verticesZOffset = l2;
            l2 += j2;
        } catch (Exception _ex) {
        }
    }

    public static boolean newmodel[];

    public static void initialise(int i, OnDemandFetcherParent onDemandFetcher) {
        modelHeader = new ModelHeader[65535];
        newmodel = new boolean[65535];
        resourceManager = onDemandFetcher;
    }

    public static void removeFromHeader(int j) {
        modelHeader[j] = null;
    }

    public static Model fetchModel(int j) {
        if (modelHeader == null)
            return null;
        if (j == 0)
            return null;
        ModelHeader class21 = modelHeader[j];
        if (class21 == null) {
            resourceManager.get(j);
            return null;
        } else {
            return new Model(j);
        }
    }

    public static boolean modelIsFetched(int i) {
        if (modelHeader == null)
            return false;

        ModelHeader class21 = modelHeader[i];
        if (class21 == null) {
            resourceManager.get(i);
            return false;
        } else {
            return true;
        }
    }

    private Model(boolean flag) {
        aBoolean1618 = true;
        rendersWithinOneTile = false;
    }

    public Model(int i, Model amodel[]) {
        aBoolean1618 = true;
        rendersWithinOneTile = false;
        anInt1620++;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        vertexCount = 0;
        triangleCount = 0;
        texturedFaces = 0;
        face_priority = -1;
        for (int k = 0; k < i; k++) {
            Model model = amodel[k];
            if (model != null) {
                vertexCount += model.vertexCount;
                triangleCount += model.triangleCount;
                texturedFaces += model.texturedFaces;
                flag |= model.triangleInfo != null;
                if (model.trianglePriorities != null) {
                    flag1 = true;
                } else {
                    if (face_priority == -1)
                        face_priority = model.face_priority;
                    if (face_priority != model.face_priority)
                        flag1 = true;
                }
                flag2 |= model.triangleAlpha != null;
                flag3 |= model.triangleLabels != null;
            }
        } // hm
        for (int k = 0; k < i; k++) {
            Model model = amodel[k];
            if (model != null) {
                vertexCount += model.vertexCount;
                triangleCount += model.triangleCount;
                texturedFaces += model.texturedFaces;
                flag |= model.triangleInfo != null;
                if (model.facePriorities != null) {
                    flag1 = true;
                } else {
                    if (face_priority == -1)
                        face_priority = model.face_priority;
                    if (face_priority != model.face_priority)
                        flag1 = true;
                }
                flag2 |= model.triangleAlpha != null;
                flag3 |= model.triangleLabels != null;
            }
        }

        verticesParticle = new int[vertexCount];
        verticesXCoordinate = new int[vertexCount];
        verticesYCoordinate = new int[vertexCount];
        verticesZCoordinate = new int[vertexCount];
        vertexLabels = new int[vertexCount];
        faceIndicesA = new int[triangleCount];
        faceIndicesB = new int[triangleCount];
        faceIndicesC = new int[triangleCount];
        textureVertexA = new int[texturedFaces];
        textureVertexB = new int[texturedFaces];
        textureVertexC = new int[texturedFaces];
        if (flag)
            triangleInfo = new int[triangleCount];
        if (flag1)
            facePriorities = new int[triangleCount];
        if (flag2)
            triangleAlpha = new int[triangleCount];
        if (flag3)
            triangleLabels = new int[triangleCount];
        triangleColors = new int[triangleCount];
        vertexCount = 0;
        triangleCount = 0;
        texturedFaces = 0;
        int l = 0;
        for (int i1 = 0; i1 < i; i1++) {
            Model model_1 = amodel[i1];
            if (model_1 != null) {
                for (int j1 = 0; j1 < model_1.triangleCount; j1++) {
                    if (flag)
                        if (model_1.triangleInfo == null) {
                            triangleInfo[triangleCount] = 0;
                        } else {
                            int k1 = model_1.triangleInfo[j1];
                            if ((k1 & 2) == 2)
                                k1 += l << 2;
                            triangleInfo[triangleCount] = k1;
                        }
                    if (flag1)
                        if (model_1.facePriorities == null)
                            facePriorities[triangleCount] = model_1.face_priority;
                        else
                            facePriorities[triangleCount] = model_1.facePriorities[j1];
                    if (flag2)
                        if (model_1.triangleAlpha == null)
                            triangleAlpha[triangleCount] = 0;
                        else
                            triangleAlpha[triangleCount] = model_1.triangleAlpha[j1];

                    if (flag3 && model_1.triangleLabels != null)
                        triangleLabels[triangleCount] = model_1.triangleLabels[j1];
                    triangleColors[triangleCount] = model_1.triangleColors[j1];
                    faceIndicesA[triangleCount] = method465(model_1, model_1.faceIndicesA[j1]);
                    faceIndicesB[triangleCount] = method465(model_1, model_1.faceIndicesB[j1]);
                    faceIndicesC[triangleCount] = method465(model_1, model_1.faceIndicesC[j1]);
                    triangleCount++;
                }

                for (int l1 = 0; l1 < model_1.texturedFaces; l1++) {
                    textureVertexA[texturedFaces] = method465(model_1, model_1.textureVertexA[l1]);
                    textureVertexB[texturedFaces] = method465(model_1, model_1.textureVertexB[l1]);
                    textureVertexC[texturedFaces] = method465(model_1, model_1.textureVertexC[l1]);
                    texturedFaces++;
                }

                l += model_1.texturedFaces;
            }
        }

    }

    public Model(Model amodel[]) {
        int i = 2;
        aBoolean1618 = true;
        rendersWithinOneTile = false;
        anInt1620++;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        vertexCount = 0;
        triangleCount = 0;
        texturedFaces = 0;
        face_priority = -1;

        for (int k = 0; k < i; k++) {
            Model model = amodel[k];
            if (model.trianglePriorities != null) {
                flag2 = true;
            } else {
                if (face_priority == -1)
                    face_priority = model.face_priority;
                if (face_priority != model.face_priority)
                    flag2 = true;
            }
            if (model != null) {
                vertexCount += model.vertexCount;
                triangleCount += model.triangleCount;
                texturedFaces += model.texturedFaces;
                flag1 |= model.triangleInfo != null;
                if (model.facePriorities != null) {
                    flag2 = true;
                } else {
                    if (face_priority == -1)
                        face_priority = model.face_priority;
                    if (face_priority != model.face_priority)
                        flag2 = true;
                }
                flag3 |= model.triangleAlpha != null;
                flag4 |= model.triangleColors != null;
            }
        }

        verticesParticle = new int[vertexCount];
        verticesXCoordinate = new int[vertexCount];
        verticesYCoordinate = new int[vertexCount];
        verticesZCoordinate = new int[vertexCount];
        faceIndicesA = new int[triangleCount];
        faceIndicesB = new int[triangleCount];
        faceIndicesC = new int[triangleCount];
        face_shade_a = new int[triangleCount];
        face_shade_b = new int[triangleCount];
        face_shade_c = new int[triangleCount];
        textureVertexA = new int[texturedFaces];
        textureVertexB = new int[texturedFaces];
        textureVertexC = new int[texturedFaces];
        if (flag1)
            triangleInfo = new int[triangleCount];
        if (flag2)
            facePriorities = new int[triangleCount];
        if (flag3)
            triangleAlpha = new int[triangleCount];
        if (flag4)
            triangleColors = new int[triangleCount];
        vertexCount = 0;
        triangleCount = 0;
        texturedFaces = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < i; j1++) {
            Model model_1 = amodel[j1];
            if (model_1 != null) {
                int k1 = vertexCount;
                for (int l1 = 0; l1 < model_1.vertexCount; l1++) {
                    verticesParticle[vertexCount] = model_1.verticesParticle[l1];
                    verticesXCoordinate[vertexCount] = model_1.verticesXCoordinate[l1];
                    verticesYCoordinate[vertexCount] = model_1.verticesYCoordinate[l1];
                    verticesZCoordinate[vertexCount] = model_1.verticesZCoordinate[l1];
                    vertexCount++;
                }

                for (int i2 = 0; i2 < model_1.triangleCount; i2++) {
                    faceIndicesA[triangleCount] = model_1.faceIndicesA[i2] + k1;
                    faceIndicesB[triangleCount] = model_1.faceIndicesB[i2] + k1;
                    faceIndicesC[triangleCount] = model_1.faceIndicesC[i2] + k1;
                    face_shade_a[triangleCount] = model_1.face_shade_a[i2];
                    face_shade_b[triangleCount] = model_1.face_shade_b[i2];
                    face_shade_c[triangleCount] = model_1.face_shade_c[i2];
                    if (flag1)
                        if (model_1.triangleInfo == null) {
                            triangleInfo[triangleCount] = 0;
                        } else {
                            int j2 = model_1.triangleInfo[i2];
                            if ((j2 & 2) == 2)
                                j2 += i1 << 2;
                            triangleInfo[triangleCount] = j2;
                        }
                    if (flag2)
                        if (model_1.facePriorities == null)
                            facePriorities[triangleCount] = model_1.face_priority;
                        else
                            facePriorities[triangleCount] = model_1.facePriorities[i2];
                    if (flag3)
                        if (model_1.triangleAlpha == null)
                            triangleAlpha[triangleCount] = 0;
                        else
                            triangleAlpha[triangleCount] = model_1.triangleAlpha[i2];
                    if (flag4 && model_1.triangleColors != null)
                        triangleColors[triangleCount] = model_1.triangleColors[i2];

                    triangleCount++;
                }

                for (int k2 = 0; k2 < model_1.texturedFaces; k2++) {
                    textureVertexA[texturedFaces] = model_1.textureVertexA[k2] + k1;
                    textureVertexB[texturedFaces] = model_1.textureVertexB[k2] + k1;
                    textureVertexC[texturedFaces] = model_1.textureVertexC[k2] + k1;
                    texturedFaces++;
                }

                i1 += model_1.texturedFaces;
            }
        }

        calculateDiagonals();
    }

    public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
        aBoolean1618 = true;
        rendersWithinOneTile = false;
        anInt1620++;
        vertexCount = model.vertexCount;
        triangleCount = model.triangleCount;
        texturedFaces = model.texturedFaces;
        if (flag2) {
            verticesParticle = model.verticesParticle;
            verticesXCoordinate = model.verticesXCoordinate;
            verticesYCoordinate = model.verticesYCoordinate;
            verticesZCoordinate = model.verticesZCoordinate;
        } else {
            verticesParticle = new int[vertexCount];
            verticesXCoordinate = new int[vertexCount];
            verticesYCoordinate = new int[vertexCount];
            verticesZCoordinate = new int[vertexCount];
            for (int j = 0; j < vertexCount; j++) {
                verticesParticle[j] = model.verticesParticle[j];
                verticesXCoordinate[j] = model.verticesXCoordinate[j];
                verticesYCoordinate[j] = model.verticesYCoordinate[j];
                verticesZCoordinate[j] = model.verticesZCoordinate[j];
            }

        }
        if (flag) {
            triangleColors = model.triangleColors;
        } else {
            triangleColors = new int[triangleCount];
            for (int k = 0; k < triangleCount; k++)
                triangleColors[k] = model.triangleColors[k];

        }
        if (flag1) {
            triangleAlpha = model.triangleAlpha;
        } else {
            triangleAlpha = new int[triangleCount];
            if (model.triangleAlpha == null) {
                for (int l = 0; l < triangleCount; l++)
                    triangleAlpha[l] = 0;

            } else {
                for (int i1 = 0; i1 < triangleCount; i1++)
                    triangleAlpha[i1] = model.triangleAlpha[i1];

            }
        }

        vertexLabels = model.vertexLabels;
        triangleLabels = model.triangleLabels;
        triangleInfo = model.triangleInfo;
        trianglePriorities = model.trianglePriorities;
        faceIndicesA = model.faceIndicesA;
        faceIndicesB = model.faceIndicesB;
        faceIndicesC = model.faceIndicesC;
        facePriorities = model.facePriorities;
        face_priority = model.face_priority;
        textureVertexA = model.textureVertexA;
        textureVertexB = model.textureVertexB;
        textureVertexC = model.textureVertexC;
    }

    public Model(boolean flag, boolean flag1, Model model) {
        aBoolean1618 = true;
        rendersWithinOneTile = false;
        anInt1620++;
        vertexCount = model.vertexCount;
        triangleCount = model.triangleCount;
        texturedFaces = model.texturedFaces;
        if (flag) {
            verticesYCoordinate = new int[vertexCount];
            for (int j = 0; j < vertexCount; j++)
                verticesYCoordinate[j] = model.verticesYCoordinate[j];

        } else {
            verticesYCoordinate = model.verticesYCoordinate;
        }
        if (flag1) {
            face_shade_a = new int[triangleCount];
            face_shade_b = new int[triangleCount];
            face_shade_c = new int[triangleCount];
            for (int k = 0; k < triangleCount; k++) {
                face_shade_a[k] = model.face_shade_a[k];
                face_shade_b[k] = model.face_shade_b[k];
                face_shade_c[k] = model.face_shade_c[k];
            }

            triangleInfo = new int[triangleCount];
            if (model.triangleInfo == null) {
                for (int l = 0; l < triangleCount; l++)
                    triangleInfo[l] = 0;

            } else {
                for (int i1 = 0; i1 < triangleCount; i1++)
                    triangleInfo[i1] = model.triangleInfo[i1];

            }
            super.vertexNormals = new VertexNormal[vertexCount];
            for (int j1 = 0; j1 < vertexCount; j1++) {
                VertexNormal vertNormal = super.vertexNormals[j1] = new VertexNormal();
                VertexNormal class33_1 = model.vertexNormals[j1];
                vertNormal.anInt602 = class33_1.anInt602;
                vertNormal.anInt603 = class33_1.anInt603;
                vertNormal.anInt604 = class33_1.anInt604;
                vertNormal.anInt605 = class33_1.anInt605;
            }

            vertexNormalOffset = model.vertexNormalOffset;
        } else {
            face_shade_a = model.face_shade_a;
            face_shade_b = model.face_shade_b;
            face_shade_c = model.face_shade_c;
            triangleInfo = model.triangleInfo;
        }
        verticesParticle = model.verticesParticle;
        verticesXCoordinate = model.verticesXCoordinate;
        verticesZCoordinate = model.verticesZCoordinate;
        triangleColors = model.triangleColors;
        triangleAlpha = model.triangleAlpha;
        facePriorities = model.facePriorities;
        trianglePriorities = model.trianglePriorities;
        face_priority = model.face_priority;
        faceIndicesA = model.faceIndicesA;
        faceIndicesB = model.faceIndicesB;
        faceIndicesC = model.faceIndicesC;
        textureVertexA = model.textureVertexA;
        textureVertexB = model.textureVertexB;
        textureVertexC = model.textureVertexC;
        super.modelHeight = model.modelHeight;

        anInt1650 = model.anInt1650;
        anInt1653 = model.anInt1653;
        diagonal3D = model.diagonal3D;
        anInt1646 = model.anInt1646;
        anInt1648 = model.anInt1648;
        anInt1649 = model.anInt1649;
        anInt1647 = model.anInt1647;
    }

    public void method464(Model model, boolean flag) {
        vertexCount = model.vertexCount;
        triangleCount = model.triangleCount;
        texturedFaces = model.texturedFaces;
        if (anIntArray1622.length < vertexCount) {
            tmpParticlesArray = new int[vertexCount + 100000];
            anIntArray1622 = new int[vertexCount + 100000];
            anIntArray1623 = new int[vertexCount + 100000];
            anIntArray1624 = new int[vertexCount + 100000];
        }
        verticesParticle = tmpParticlesArray;
        verticesXCoordinate = anIntArray1622;
        verticesYCoordinate = anIntArray1623;
        verticesZCoordinate = anIntArray1624;
        for (int k = 0; k < vertexCount; k++) {
            verticesParticle[k] = model.verticesParticle[k];
            verticesXCoordinate[k] = model.verticesXCoordinate[k];
            verticesYCoordinate[k] = model.verticesYCoordinate[k];
            verticesZCoordinate[k] = model.verticesZCoordinate[k];
        }

        if (flag) {
            triangleAlpha = model.triangleAlpha;
        } else {
            if (anIntArray1625.length < triangleCount)
                anIntArray1625 = new int[triangleCount + 100];
            triangleAlpha = anIntArray1625;
            if (model.triangleAlpha == null) {
                for (int l = 0; l < triangleCount; l++)
                    triangleAlpha[l] = 0;

            } else {
                for (int i1 = 0; i1 < triangleCount; i1++)
                    triangleAlpha[i1] = model.triangleAlpha[i1];

            }
        }
        triangleInfo = model.triangleInfo;
        triangleColors = model.triangleColors;
        facePriorities = model.facePriorities;
        trianglePriorities = model.trianglePriorities;
        face_priority = model.face_priority;
        groupedTriangleLabels = model.groupedTriangleLabels;
        groupedLabels = model.groupedLabels;
        faceIndicesA = model.faceIndicesA;
        faceIndicesB = model.faceIndicesB;
        faceIndicesC = model.faceIndicesC;
        face_shade_a = model.face_shade_a;
        face_shade_b = model.face_shade_b;
        face_shade_c = model.face_shade_c;
        textureVertexA = model.textureVertexA;
        textureVertexB = model.textureVertexB;
        textureVertexC = model.textureVertexC;
    }

    private final int method465(Model model, int i) {
        int p = model.verticesParticle[i];
        int j = -1;
        int k = model.verticesXCoordinate[i];
        int l = model.verticesYCoordinate[i];
        int i1 = model.verticesZCoordinate[i];
        for (int j1 = 0; j1 < vertexCount; j1++) {
            if (k != verticesXCoordinate[j1] || l != verticesYCoordinate[j1] || i1 != verticesZCoordinate[j1])
                continue;
            j = j1;
            break;
        }

        if (j == -1) {
            verticesParticle[vertexCount] = p;
            verticesXCoordinate[vertexCount] = k;
            verticesYCoordinate[vertexCount] = l;
            verticesZCoordinate[vertexCount] = i1;
            if (model.vertexLabels != null)
                vertexLabels[vertexCount] = model.vertexLabels[i];
            j = vertexCount++;
        }
        return j;
    }

    public void calculateDiagonals() {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        for (int i = 0; i < vertexCount; i++) {
            int j = verticesXCoordinate[i];
            int k = verticesYCoordinate[i];
            int l = verticesZCoordinate[i];
            if (-k > super.modelHeight)
                super.modelHeight = -k;
            if (k > anInt1651)
                anInt1651 = k;
            int i1 = j * j + l * l;
            if (i1 > anInt1650)
                anInt1650 = i1;
        }
        anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
                + 0.98999999999999999D);
        diagonal3D = anInt1653
                + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    public void normalise() {
        super.modelHeight = 0;
        anInt1651 = 0;
        for (int i = 0; i < vertexCount; i++) {
            int j = verticesYCoordinate[i];
            if (-j > super.modelHeight)
                super.modelHeight = -j;
            if (j > anInt1651)
                anInt1651 = j;
        }

        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
                + 0.98999999999999999D);
        diagonal3D = anInt1653
                + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    public void calcDiagonalsAndStats(int i) {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        anInt1646 = 0xf423f;
        anInt1647 = 0xfff0bdc1;
        anInt1648 = 0xfffe7961;
        anInt1649 = 0x1869f;
        for (int j = 0; j < vertexCount; j++) {
            int k = verticesXCoordinate[j];
            int l = verticesYCoordinate[j];
            int i1 = verticesZCoordinate[j];
            if (k < anInt1646)
                anInt1646 = k;
            if (k > anInt1647)
                anInt1647 = k;
            if (i1 < anInt1649)
                anInt1649 = i1;
            if (i1 > anInt1648)
                anInt1648 = i1;
            if (-l > super.modelHeight)
                super.modelHeight = -l;
            if (l > anInt1651)
                anInt1651 = l;
            int j1 = k * k + i1 * i1;
            if (j1 > anInt1650)
                anInt1650 = j1;
        }

        anInt1650 = (int) Math.sqrt(anInt1650);
        anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight);
        if (i != 21073) {
            return;
        } else {
            diagonal3D = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
            return;
        }
    }

    public void createBones() {
        if (vertexLabels != null) {
            int ai[] = new int[256];
            int j = 0;
            for (int l = 0; l < vertexCount; l++) {
                int j1 = vertexLabels[l];
                ai[j1]++;
                if (j1 > j)
                    j = j1;
            }
            groupedLabels = null;
            groupedLabels = new int[j + 1][];
            for (int k1 = 0; k1 <= j; k1++) {
                groupedLabels[k1] = new int[ai[k1]];
                ai[k1] = 0;
            }

            for (int j2 = 0; j2 < vertexCount; j2++) {
                int l2 = vertexLabels[j2];
                groupedLabels[l2][ai[l2]++] = j2;
            }

            vertexLabels = null;
            ai = null;
        }
        if (triangleLabels != null) {
            int ai1[] = new int[256];
            int k = 0;
            for (int i1 = 0; i1 < triangleCount; i1++) {
                int l1 = triangleLabels[i1];
                ai1[l1]++;
                if (l1 > k)
                    k = l1;
            }
            groupedTriangleLabels = null;
            groupedTriangleLabels = new int[k + 1][];
            for (int i2 = 0; i2 <= k; i2++) {
                groupedTriangleLabels[i2] = new int[ai1[i2]];
                ai1[i2] = 0;
            }

            for (int k2 = 0; k2 < triangleCount; k2++) {
                int i3 = triangleLabels[k2];
                groupedTriangleLabels[i3][ai1[i3]++] = k2;
            }

            triangleLabels = null;
            ai1 = null;
        }
    }

    public void applyTransform(int i) {
        if (groupedLabels == null)
            return;
        if (i == -1)
            return;
        FrameReader class36 = FrameReader.forID(i);
        if (class36 == null)
            return;
        SkinList class18 = class36.mySkinList;
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        for (int k = 0; k < class36.stepCount; k++) {
            int l = class36.opCodeLinkTable[k];
            method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
                    class36.zOffset[k]);
        }

    }

    public void applyTransform(int firstFrame, int nextFrame, int end, int cycle) {

        try {
            if (groupedLabels != null && firstFrame != -1) {
                FrameReader currentAnimation = FrameReader.forID(firstFrame);
                SkinList list1 = currentAnimation.mySkinList;
                anInt1681 = 0;
                anInt1682 = 0;
                anInt1683 = 0;
                FrameReader nextAnimation = null;
                SkinList list2 = null;
                if (nextFrame != -1) {
                    nextAnimation = FrameReader.forID(nextFrame);
                    if (nextAnimation.mySkinList != list1)
                        nextAnimation = null;
                    list2 = nextAnimation.mySkinList;
                }
                if (nextAnimation == null || list2 == null) {
                    for (int i_263_ = 0; i_263_ < currentAnimation.stepCount; i_263_++) {
                        int i_264_ = currentAnimation.opCodeLinkTable[i_263_];
                        method472(list1.opcodes[i_264_], list1.skinList[i_264_], currentAnimation.xOffset[i_263_],
                                currentAnimation.yOffset[i_263_], currentAnimation.zOffset[i_263_]);

                    }
                } else {
                    for (int i1 = 0; i1 < currentAnimation.stepCount; i1++) {
                        int n1 = currentAnimation.opCodeLinkTable[i1];
                        int opcode = list1.opcodes[n1];
                        int[] skin = list1.skinList[n1];
                        int x = currentAnimation.xOffset[i1];
                        int y = currentAnimation.yOffset[i1];
                        int z = currentAnimation.zOffset[i1];
                        boolean found = false;
                        for (int i2 = 0; i2 < nextAnimation.stepCount; i2++) {
                            int n2 = nextAnimation.opCodeLinkTable[i2];
                            if (list2.skinList[n2].equals(skin)) {
                                if (opcode != 2) {
                                    x += (nextAnimation.xOffset[i2] - x) * cycle / end;
                                    y += (nextAnimation.yOffset[i2] - y) * cycle / end;
                                    z += (nextAnimation.zOffset[i2] - z) * cycle / end;
                                } else {
                                    int dx = nextAnimation.xOffset[i2] - x & 0x7ff;
                                    int dy = nextAnimation.yOffset[i2] - y & 0x7ff;
                                    int dz = nextAnimation.zOffset[i2] - z & 0x7ff;
                                    if (dx >= 1024) {
                                        dx -= 2048;
                                    }
                                    if (dy >= 1024) {
                                        dy -= 2048;
                                    }
                                    if (dz >= 1024) {
                                        dz -= 2048;
                                    }
                                    x = x + dx * cycle / end & 0x7ff;
                                    y = y + dy * cycle / end & 0x7ff;
                                    z = z + dz * cycle / end & 0x7ff;
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            if (opcode != 3 && opcode != 2) {
                                x = x * (end - cycle) / end;
                                y = y * (end - cycle) / end;
                                z = z * (end - cycle) / end;
                            } else if (opcode == 3) {
                                x = (x * (end - cycle) + (cycle << 7)) / end;
                                y = (y * (end - cycle) + (cycle << 7)) / end;
                                z = (z * (end - cycle) + (cycle << 7)) / end;
                            } else {
                                int dx = -x & 0x7ff;
                                int dy = -y & 0x7ff;
                                int dz = -z & 0x7ff;
                                if (dx >= 1024) {
                                    dx -= 2048;
                                }
                                if (dy >= 1024) {
                                    dy -= 2048;
                                }
                                if (dz >= 1024) {
                                    dz -= 2048;
                                }
                                x = x + dx * cycle / end & 0x7ff;
                                y = y + dy * cycle / end & 0x7ff;
                                z = z + dz * cycle / end & 0x7ff;
                            }
                        }
                        method472(opcode, skin, x, y, z);
                    }
                }
            }

        } catch (Exception e) {
            // e.printStackTrace();
            applyTransform(firstFrame); // Cheap fix
        }
    }

    public void applyTransform_2(int i, int frame2) {
        if (groupedLabels == null)
            return;
        if (i == -1)
            return;
        FrameReader class36 = FrameReader.forID(i);
        if (class36 == null)
            return;
        FrameReader class36_1 = FrameReader.forID(frame2);
        FrameReader fr = FrameReader.getTween(class36, class36_1);
        class36 = fr;
        SkinList class18 = class36.mySkinList;
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        for (int k = 0; k < class36.stepCount; k++) {
            int l = class36.opCodeLinkTable[k];
            method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
                    class36.zOffset[k]);
        }

    }

    public void method471(int ai[], int j, int k) {
        if (k == -1)
            return;
        if (ai == null || j == -1) {
            applyTransform(k);
            return;
        }
        FrameReader class36 = FrameReader.forID(k);
        if (class36 == null)
            return;
        FrameReader class36_1 = FrameReader.forID(j);
        if (class36_1 == null) {
            applyTransform(k);
            return;
        }
        SkinList class18 = class36.mySkinList;
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        int l = 0;
        int i1 = ai[l++];
        for (int j1 = 0; j1 < class36.stepCount; j1++) {
            int k1;
            for (k1 = class36.opCodeLinkTable[j1]; k1 > i1; i1 = ai[l++])
                ;
            if (k1 != i1 || class18.opcodes[k1] == 0)
                method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1],
                        class36.zOffset[j1]);
        }

        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        l = 0;
        i1 = ai[l++];
        try {
            for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
                int i2;
                for (i2 = class36_1.opCodeLinkTable[l1]; i2 > i1; i1 = ai[l++])
                    ;
                if (i2 == i1 || class18.opcodes[i2] == 0)
                    method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1],
                            class36_1.zOffset[l1]);
            }
        } catch (Exception e) {
        }

    }

    public void method471_2(int mixingData[], int j, int frameId, int frameId2) {
        if (frameId == -1)
            return;
        if (mixingData == null || j == -1) {
            applyTransform(frameId);
            return;
        }
        FrameReader class36 = FrameReader.forID(frameId);
        if (class36 == null)
            return;
        FrameReader class36_1 = FrameReader.forID(j);
        if (class36_1 == null) {
            applyTransform(frameId);
            return;
        }
        FrameReader fr = FrameReader.forID(frameId2);
        FrameReader tween = FrameReader.getTween(class36_1, fr);
        class36 = tween;
        SkinList class18 = class36.mySkinList;
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        int l = 0;
        int i1 = mixingData[l++];
        for (int j1 = 0; j1 < class36.stepCount; j1++) {
            int k1;
            for (k1 = class36.opCodeLinkTable[j1]; k1 > i1; i1 = mixingData[l++])
                ;
            if (k1 != i1 || class18.opcodes[k1] == 0)
                method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1],
                        class36.zOffset[j1]);
        }

        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        l = 0;
        i1 = mixingData[l++];
        for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
            int i2;
            for (i2 = class36_1.opCodeLinkTable[l1]; i2 > i1; i1 = mixingData[l++])
                ;
            if (i2 == i1 || class18.opcodes[i2] == 0)
                method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1],
                        class36_1.zOffset[l1]);
        }

    }

    private void method472(int i, int ai[], int j, int k, int l) {

        int i1 = ai.length;
        if (i == 0) {
            int j1 = 0;
            anInt1681 = 0;
            anInt1682 = 0;
            anInt1683 = 0;
            for (int k2 = 0; k2 < i1; k2++) {
                int l3 = ai[k2];
                if (l3 < groupedLabels.length) {
                    int ai5[] = groupedLabels[l3];
                    for (int i5 = 0; i5 < ai5.length; i5++) {
                        int j6 = ai5[i5];
                        anInt1681 += verticesXCoordinate[j6];
                        anInt1682 += verticesYCoordinate[j6];
                        anInt1683 += verticesZCoordinate[j6];
                        j1++;
                    }

                }
            }

            if (j1 > 0) {
                anInt1681 = anInt1681 / j1 + j;
                anInt1682 = anInt1682 / j1 + k;
                anInt1683 = anInt1683 / j1 + l;
                return;
            } else {
                anInt1681 = j;
                anInt1682 = k;
                anInt1683 = l;
                return;
            }
        }
        if (i == 1) {
            for (int k1 = 0; k1 < i1; k1++) {
                int l2 = ai[k1];
                if (l2 < groupedLabels.length) {
                    int ai1[] = groupedLabels[l2];
                    for (int i4 = 0; i4 < ai1.length; i4++) {
                        int j5 = ai1[i4];
                        verticesXCoordinate[j5] += j;
                        verticesYCoordinate[j5] += k;
                        verticesZCoordinate[j5] += l;
                    }

                }
            }

            return;
        }
        if (i == 2) {
            for (int l1 = 0; l1 < i1; l1++) {
                int i3 = ai[l1];
                if (i3 < groupedLabels.length) {
                    int ai2[] = groupedLabels[i3];
                    for (int j4 = 0; j4 < ai2.length; j4++) {
                        int k5 = ai2[j4];
                        verticesXCoordinate[k5] -= anInt1681;
                        verticesYCoordinate[k5] -= anInt1682;
                        verticesZCoordinate[k5] -= anInt1683;
                        if (l != 0) {
                            int j7 = SINE[l];
                            int i8 = COSINE[l];
                            int l8 = verticesYCoordinate[k5] * j7 + verticesXCoordinate[k5] * i8 >> 16;
                            verticesYCoordinate[k5] = verticesYCoordinate[k5] * i8 - verticesXCoordinate[k5] * j7 >> 16;
                            verticesXCoordinate[k5] = l8;
                        }
                        if (j != 0) {
                            int k7 = SINE[j];
                            int j8 = COSINE[j];
                            int i9 = verticesYCoordinate[k5] * j8 - verticesZCoordinate[k5] * k7 >> 16;
                            verticesZCoordinate[k5] = verticesYCoordinate[k5] * k7 + verticesZCoordinate[k5] * j8 >> 16;
                            verticesYCoordinate[k5] = i9;
                        }
                        if (k != 0) {
                            int l7 = SINE[k];
                            int k8 = COSINE[k];
                            int j9 = verticesZCoordinate[k5] * l7 + verticesXCoordinate[k5] * k8 >> 16;
                            verticesZCoordinate[k5] = verticesZCoordinate[k5] * k8 - verticesXCoordinate[k5] * l7 >> 16;
                            verticesXCoordinate[k5] = j9;
                        }
                        verticesXCoordinate[k5] += anInt1681;
                        verticesYCoordinate[k5] += anInt1682;
                        verticesZCoordinate[k5] += anInt1683;
                    }

                }
            }
            return;
        }
        if (i == 3) {
            for (int i2 = 0; i2 < i1; i2++) {
                int j3 = ai[i2];
                if (j3 < groupedLabels.length) {
                    int ai3[] = groupedLabels[j3];
                    for (int k4 = 0; k4 < ai3.length; k4++) {
                        int l5 = ai3[k4];
                        verticesXCoordinate[l5] -= anInt1681;
                        verticesYCoordinate[l5] -= anInt1682;
                        verticesZCoordinate[l5] -= anInt1683;
                        verticesXCoordinate[l5] = (verticesXCoordinate[l5] * j) / 128;
                        verticesYCoordinate[l5] = (verticesYCoordinate[l5] * k) / 128;
                        verticesZCoordinate[l5] = (verticesZCoordinate[l5] * l) / 128;
                        verticesXCoordinate[l5] += anInt1681;
                        verticesYCoordinate[l5] += anInt1682;
                        verticesZCoordinate[l5] += anInt1683;
                    }
                }
            }
            return;
        }
        if (i == 5 && groupedTriangleLabels != null && triangleAlpha != null) {
            for (int j2 = 0; j2 < i1; j2++) {
                int k3 = ai[j2];
                if (k3 < groupedTriangleLabels.length) {
                    int ai4[] = groupedTriangleLabels[k3];
                    for (int l4 = 0; l4 < ai4.length; l4++) {
                        int i6 = ai4[l4];
                        (triangleAlpha)[i6] += j * 8;
                        if ((triangleAlpha)[i6] < 0)
                            (triangleAlpha)[i6] = 0;
                        if ((triangleAlpha)[i6] > 255)
                            (triangleAlpha)[i6] = 255;
                    }
                }
            }
        }
    }

    public void rotateBy90() {
        for (int j = 0; j < vertexCount; j++) {
            int k = verticesXCoordinate[j];
            verticesXCoordinate[j] = verticesZCoordinate[j];
            verticesZCoordinate[j] = -k;
        }
    }

    public void rotateX(int i) {
        int k = SINE[i];
        int l = COSINE[i];
        for (int i1 = 0; i1 < vertexCount; i1++) {
            int j1 = verticesYCoordinate[i1] * l - verticesZCoordinate[i1] * k >> 16;
            verticesZCoordinate[i1] = verticesYCoordinate[i1] * k + verticesZCoordinate[i1] * l >> 16;
            verticesYCoordinate[i1] = j1;
        }
    }

    public void translate(int i, int j, int l) {
        for (int i1 = 0; i1 < vertexCount; i1++) {
            verticesXCoordinate[i1] += i;
            verticesYCoordinate[i1] += j;
            verticesZCoordinate[i1] += l;
        }
    }

    public void recolour(int itemId, int editedColor, int originalColor) {
        for (int k = 0; k < triangleColors.length; k++) {
            if (triangleColors[k] == editedColor) {
                triangleColors[k] = originalColor;
            }
        }
    }

    public void recolour(int i, int j) {
        for (int k = 0; k < triangleCount; k++)
            if (triangleColors[k] == i)
                triangleColors[k] = j;
    }

    public void mirrorModel() {
        for (int j = 0; j < vertexCount; j++)
            verticesZCoordinate[j] = -verticesZCoordinate[j];
        for (int k = 0; k < triangleCount; k++) {
            int l = faceIndicesA[k];
            faceIndicesA[k] = faceIndicesC[k];
            faceIndicesC[k] = l;
        }
    }

    public void scaleT(int i, int j, int l) {
        for (int i1 = 0; i1 < vertexCount; i1++) {
            verticesXCoordinate[i1] = (verticesXCoordinate[i1] * i) / 128;
            verticesYCoordinate[i1] = (verticesYCoordinate[i1] * l) / 128;
            verticesZCoordinate[i1] = (verticesZCoordinate[i1] * j) / 128;
        }

    }

    public void light(int i, int j, int k, int l, int i1, boolean flag) {
        try {
            int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
            int k1 = j * j1 >> 8;
            if (face_shade_a == null) {
                face_shade_a = new int[triangleCount];
                face_shade_b = new int[triangleCount];
                face_shade_c = new int[triangleCount];
            }
            if (super.vertexNormals == null) {
                super.vertexNormals = new VertexNormal[vertexCount];
                for (int l1 = 0; l1 < vertexCount; l1++)
                    super.vertexNormals[l1] = new VertexNormal();

            }
            for (int i2 = 0; i2 < triangleCount; i2++) {
                if (triangleColors != null && triangleAlpha != null) {
                    if (triangleColors[i2] == 65535 || triangleColors[i2] == 1 || triangleColors[i2] == 16705
                            || triangleColors[i2] == 255)
                        triangleAlpha[i2] = 255;
                }
                int j2 = faceIndicesA[i2];
                int l2 = faceIndicesB[i2];
                int i3 = faceIndicesC[i2];
                int j3 = verticesXCoordinate[l2] - verticesXCoordinate[j2];
                int k3 = verticesYCoordinate[l2] - verticesYCoordinate[j2];
                int l3 = verticesZCoordinate[l2] - verticesZCoordinate[j2];
                int i4 = verticesXCoordinate[i3] - verticesXCoordinate[j2];
                int j4 = verticesYCoordinate[i3] - verticesYCoordinate[j2];
                int k4 = verticesZCoordinate[i3] - verticesZCoordinate[j2];
                int l4 = k3 * k4 - j4 * l3;
                int i5 = l3 * i4 - k4 * j3;
                int j5;
                for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192
                        || j5 < -8192; j5 >>= 1) {
                    l4 >>= 1;
                    i5 >>= 1;
                }

                int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
                if (k5 <= 0)
                    k5 = 1;
                l4 = (l4 * 256) / k5;
                i5 = (i5 * 256) / k5;
                j5 = (j5 * 256) / k5;

                if (triangleInfo == null || (triangleInfo[i2] & 1) == 0) {

                    VertexNormal vNormal = super.vertexNormals[j2];
                    vNormal.anInt602 += l4;
                    vNormal.anInt603 += i5;
                    vNormal.anInt604 += j5;
                    vNormal.anInt605++;
                    vNormal = super.vertexNormals[l2];
                    vNormal.anInt602 += l4;
                    vNormal.anInt603 += i5;
                    vNormal.anInt604 += j5;
                    vNormal.anInt605++;
                    vNormal = super.vertexNormals[i3];
                    vNormal.anInt602 += l4;
                    vNormal.anInt603 += i5;
                    vNormal.anInt604 += j5;
                    vNormal.anInt605++;
                    vNormal = null;

                } else {

                    int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
                    face_shade_a[i2] = method481(triangleColors[i2], l5, triangleInfo[i2]);

                }
            }

            if (flag) {
                method480(i, k1, k, l, i1);
            } else {
                vertexNormalOffset = new VertexNormal[vertexCount];
                for (int k2 = 0; k2 < vertexCount; k2++) {
                    VertexNormal vNormal = super.vertexNormals[k2];
                    vertexNormalOffset[k2] = new VertexNormal();
                    vertexNormalOffset[k2].anInt602 = vNormal.anInt602;
                    vertexNormalOffset[k2].anInt603 = vNormal.anInt603;
                    vertexNormalOffset[k2].anInt604 = vNormal.anInt604;
                    vertexNormalOffset[k2].anInt605 = vNormal.anInt605;
                }

            }
            if (flag) {
                calculateDiagonals();
                return;
            } else {
                calcDiagonalsAndStats(21073);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ccString = "Cla";
    public static String xxString = "at Cl";
    public static String vvString = "nt";
    public static String aString9_9 = "" + ccString + "n Ch" + xxString + "ie" + vvString + " ";

    public void method480(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < triangleCount; j1++) {
            int k1 = faceIndicesA[j1];
            int i2 = faceIndicesB[j1];
            int j2 = faceIndicesC[j1];
            if (triangleInfo == null) {
                int i3 = triangleColors[j1];
                VertexNormal class33 = super.vertexNormals[k1];
                int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604)
                        / (j * class33.anInt605);
                face_shade_a[j1] = method481(i3, k2, 0);
                class33 = super.vertexNormals[i2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                face_shade_b[j1] = method481(i3, k2, 0);
                class33 = super.vertexNormals[j2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                face_shade_c[j1] = method481(i3, k2, 0);
            } else if ((triangleInfo[j1] & 1) == 0) {
                int j3 = triangleColors[j1];
                int k3 = triangleInfo[j1];
                VertexNormal class33_1 = super.vertexNormals[k1];
                int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
                        / (j * class33_1.anInt605);
                face_shade_a[j1] = method481(j3, l2, k3);
                class33_1 = super.vertexNormals[i2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
                        / (j * class33_1.anInt605);
                face_shade_b[j1] = method481(j3, l2, k3);
                class33_1 = super.vertexNormals[j2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
                        / (j * class33_1.anInt605);
                face_shade_c[j1] = method481(j3, l2, k3);
            }
        }

        super.vertexNormals = null;
        vertexNormalOffset = null;
        vertexLabels = null;
        triangleLabels = null;
        if (triangleInfo != null) {
            for (int l1 = 0; l1 < triangleCount; l1++)
                if ((triangleInfo[l1] & 2) == 2)
                    return;

        }
        // face_color = null;
    }

    public static int method481(int i, int j, int k) {
        if (i == 65535)
            return 0;
        if ((k & 2) == 2) {
            if (j < 0)
                j = 0;
            else if (j > 127)
                j = 127;
            j = 127 - j;
            return j;
        }

        j = j * (i & 0x7f) >> 7;
        if (j < 2)
            j = 2;
        else if (j > 126)
            j = 126;
        return (i & 0xff80) + j;
    }

    public void renderSingle(int rotation_2, int offsetX, int rotation_1, int offsetY, int zoom_sine, int zoom_cosine) {
        try {
            int i = 0;
            int base_draw_x = Rasterizer.textureInt1;
            int base_draw_y = Rasterizer.textureInt2;
            int base_sine = SINE[i];
            int base_cosine = COSINE[i];
            int rot_2_sine = SINE[rotation_2];
            int rot_2_cosine = COSINE[rotation_2];
            int offsetX_sine = SINE[offsetX];
            int offsetX_cosine = COSINE[offsetX];
            int rot_1_sine = SINE[rotation_1];
            int rot_1_cosine = COSINE[rotation_1];
            int calculated_zoom = zoom_sine * rot_1_sine + zoom_cosine * rot_1_cosine >> 16;
            for (int vertexId = 0; vertexId < vertexCount; vertexId++) {
                int baseVertexX = verticesXCoordinate[vertexId];
                int baseVertexY = verticesYCoordinate[vertexId];
                int baseVertexZ = verticesZCoordinate[vertexId];
                if (offsetX != 0) {
                    int calculatedVertexX = baseVertexY * offsetX_sine + baseVertexX * offsetX_cosine >> 16;
                    baseVertexY = baseVertexY * offsetX_cosine - baseVertexX * offsetX_sine >> 16;
                    baseVertexX = calculatedVertexX;
                }
                if (i != 0) {
                    int calculatedVertexY = baseVertexY * base_cosine - baseVertexZ * base_sine >> 16;
                    baseVertexZ = baseVertexY * base_sine + baseVertexZ * base_cosine >> 16;
                    baseVertexY = calculatedVertexY;
                }
                if (rotation_2 != 0) {
                    int calculatedVertexZ = baseVertexZ * rot_2_sine + baseVertexX * rot_2_cosine >> 16;
                    baseVertexZ = baseVertexZ * rot_2_cosine - baseVertexX * rot_2_sine >> 16;
                    baseVertexX = calculatedVertexZ;
                }
                baseVertexX += offsetY;
                baseVertexY += zoom_sine;
                baseVertexZ += zoom_cosine;
                int j6 = baseVertexY * rot_1_cosine - baseVertexZ * rot_1_sine >> 16;
                baseVertexZ = baseVertexY * rot_1_sine + baseVertexZ * rot_1_cosine >> 16;
                baseVertexY = j6;
                vertexPerspectiveDepth[vertexId] = baseVertexZ - calculated_zoom;
                projected_vertex_x[vertexId] = base_draw_x + (baseVertexX << 9) / baseVertexZ;
                projected_vertex_y[vertexId] = base_draw_y + (baseVertexY << 9) / baseVertexZ;
                projected_vertex_z[vertexId] = baseVertexZ;
                if (texturedFaces > 0) {
                    camera_vertex_y[vertexId] = baseVertexX;
                    camera_vertex_x[vertexId] = baseVertexY;
                    camera_vertex_z[vertexId] = baseVertexZ;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            translateToScreen(false, false, 0, -1);
            return;
        } catch (Exception _ex) {
          //  _ex.printStackTrace();
            return;
        }
    }

    public static int farClip = 3500;

    @Override
    public void renderAtPoint(int orientation, int j, int k, int l, int i1, int offsetX, int offsetY, int offsetZ, int i2, int id) {
        renderAtPointX = offsetX + GameClient.instance.xCameraPos;
        renderAtPointY = offsetZ + GameClient.instance.yCameraPos;
        renderAtPointZ = offsetY + GameClient.instance.zCameraPos;
        lastRenderedRotation = orientation;
        int j2 = offsetZ * i1 - offsetX * l >> 16;
        int k2 = offsetY * j + j2 * k >> 16;
        int l2 = anInt1650 * k >> 16;
        int i3 = k2 + l2;

        if (i3 <= 50 || k2 >= farClip)
            return;
        int j3 = offsetZ * l + offsetX * i1 >> 16;
        int k3 = (j3 - anInt1650) * WorldController.focalLength;
        if (k3 / i3 >= DrawingArea.viewport_centerX)
            return;
        int l3 = (j3 + anInt1650) * WorldController.focalLength;
        if (l3 / i3 <= -DrawingArea.viewport_centerX)
            return;
        int i4 = offsetY * k - j2 * j >> 16;
        int j4 = anInt1650 * j >> 16;
        int k4 = (i4 + j4) * WorldController.focalLength;
        if (k4 / i3 <= -DrawingArea.viewport_centerY)
            return;
        int l4 = j4 + (super.modelHeight * k >> 16);
        int i5 = (i4 - l4) * WorldController.focalLength;
        if (i5 / i3 >= DrawingArea.viewport_centerY)
            return;
        int j5 = l2 + (super.modelHeight * j >> 16);
        boolean flag = false;
        if (k2 - j5 <= 50)
            flag = true;
        boolean flag1 = false;
        if (i2 > 0 && objectExists) {
            int k5 = k2 - l2;
            if (k5 <= 50)
                k5 = 50;
            if (j3 > 0) {
                k3 /= i3;
                l3 /= k5;
            } else {
                l3 /= i3;
                k3 /= k5;
            }
            if (i4 > 0) {
                i5 /= i3;
                k4 /= k5;
            } else {
                k4 /= i3;
                i5 /= k5;
            }
            int i6 = currentCursorX - Rasterizer.textureInt1;
            int k6 = currentCursorY - Rasterizer.textureInt2;
            if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4)
                if (rendersWithinOneTile) {
                    mapObjectIds[objectsRendered] = id;
                    objectsInCurrentRegion[objectsRendered++] = i2;
                } else
                    flag1 = true;
        }
        int l5 = Rasterizer.textureInt1;
        int j6 = Rasterizer.textureInt2;
        int l6 = 0;
        int i7 = 0;
        if (orientation != 0) {
            l6 = SINE[orientation];
            i7 = COSINE[orientation];
        }
        for (int j7 = 0; j7 < vertexCount; j7++) {
            int k7 = verticesXCoordinate[j7];
            int l7 = verticesYCoordinate[j7];
            int i8 = verticesZCoordinate[j7];
            if (orientation != 0) {
                int j8 = i8 * l6 + k7 * i7 >> 16;
                i8 = i8 * i7 - k7 * l6 >> 16;
                k7 = j8;
            }
            k7 += offsetX;
            l7 += offsetY;
            i8 += offsetZ;
            int k8 = i8 * l + k7 * i1 >> 16;
            i8 = i8 * i1 - k7 * l >> 16;
            k7 = k8;
            k8 = l7 * k - i8 * j >> 16;
            i8 = l7 * j + i8 * k >> 16;
            l7 = k8;
            vertexPerspectiveDepth[j7] = i8 - k2;
            if (i8 >= 50) {
                projected_vertex_x[j7] = l5 + k7 * WorldController.focalLength / i8;
                projected_vertex_y[j7] = j6 + l7 * WorldController.focalLength / i8;
                projected_vertex_z[j7] = i8;
            } else {
                projected_vertex_x[j7] = -5000;
                flag = true;
            }
            if (flag || texturedFaces > 0) {
                camera_vertex_y[j7] = k7;
                camera_vertex_x[j7] = l7;
                camera_vertex_z[j7] = i8;
            }
        }

        try {
            translateToScreen(flag, flag1, i2, id);
            return;
        } catch (Exception _ex) {
            //_ex.printStackTrace();
            return;
        }
    }

    private final void translateToScreen(boolean flag, boolean needAddToSelectedObjects, int i, int id) {

        if (GameClient.enableParticles) {
            for (int vertex = 0; vertex < vertexCount; ++vertex) {
                int pid = verticesParticle[vertex] - 1;
                if (pid >= 0) {
                    ParticleDefinition def = ParticleDefinition.cache[pid];
                    //  System.out.println("DEF = " + def);
                    int startColor = def.getStartColor();

                    int x = verticesXCoordinate[vertex];
                    int y = verticesYCoordinate[vertex];
                    int z = verticesZCoordinate[vertex];
                    //  System.out.println("X = " + x + " Y = " + y + " z = " + z);
                    int depth = vertexPerspectiveDepth[vertex];
                    // System.out.println("DEPTH = " + depth);
                    if (lastRenderedRotation != 0) {
                        int sine = SINE[lastRenderedRotation];
                        int cosine = COSINE[lastRenderedRotation];
                        int rotatedX = z * sine + x * cosine >> 16;
                        z = z * cosine - x * sine >> 16;
                        x = rotatedX;
                    }
                    x += renderAtPointX;
                    z += renderAtPointY;


                    ParticleVector pos = new ParticleVector(x, -y, z);


                    for (int p = 0; p < def.getSpawnRate(); p++) {
                        Particle particle = new Particle(def, pos, depth, pid);
                        if (def.areColorsSet()) {
                            particle.setColor(def.getNextColor());
                        } else {
                            particle.setColor(startColor);
                        }

                        //    System.out.println("COLOR = " + particle.getColor());

                        // System.out.println("added");

                        GameClient.instance.addParticle(particle);
                    }
                }
            }

        }

        for (int j = 0; j < diagonal3D; j++)
            depthListIndices[j] = 0;

        // filterTriangles();
        for (int triangleId = 0; triangleId < triangleCount; triangleId++) {
            if (triangleInfo != null && triangleInfo[triangleId] == -1
                    || triangleAlpha != null && triangleAlpha[triangleId] >= 255)
                continue;
            int face_a_pos = faceIndicesA[triangleId];
            int face_b_pos = faceIndicesB[triangleId];
            int face_c_pos = faceIndicesC[triangleId];

            int vertexXA = projected_vertex_x[face_a_pos];
            int vertexXB = projected_vertex_x[face_b_pos];
            int vertexXC = projected_vertex_x[face_c_pos];
            if (flag && (vertexXA == -5000 || vertexXB == -5000 || vertexXC == -5000)) {
                outOfReach[triangleId] = true;
                int j5 = (vertexPerspectiveDepth[face_a_pos] + vertexPerspectiveDepth[face_b_pos] + vertexPerspectiveDepth[face_c_pos]) / 3
                        + anInt1653;
                faceLists[j5][depthListIndices[j5]++] = triangleId;
                continue;
            }
            if (needAddToSelectedObjects && cursorOn(currentCursorX, currentCursorY, projected_vertex_y[face_a_pos],
                    projected_vertex_y[face_b_pos], projected_vertex_y[face_c_pos], vertexXA, vertexXB, vertexXC)) {
                mapObjectIds[objectsRendered] = id;
                objectsInCurrentRegion[objectsRendered++] = i;
                needAddToSelectedObjects = false;
            }
            if ((vertexXA - vertexXB) * (projected_vertex_y[face_c_pos] - projected_vertex_y[face_b_pos])
                    - (projected_vertex_y[face_a_pos] - projected_vertex_y[face_b_pos]) * (vertexXC - vertexXB) > 0) {
                outOfReach[triangleId] = false;
                hasAnEdgeToRestrict[triangleId] = vertexXA < 0 || vertexXB < 0 || vertexXC < 0
                        || vertexXA > DrawingArea.viewportRX || vertexXB > DrawingArea.viewportRX
                        || vertexXC > DrawingArea.viewportRX;
                int k5 = (vertexPerspectiveDepth[face_a_pos] + vertexPerspectiveDepth[face_b_pos] + vertexPerspectiveDepth[face_c_pos]) / 3
                        + anInt1653;
                faceLists[k5][depthListIndices[k5]++] = triangleId;
            }
        }

        if (facePriorities == null) {
            for (int i1 = diagonal3D - 1; i1 >= 0; i1--) {
                int l1 = depthListIndices[i1];
                if (l1 > 0) {
                    int ai[] = faceLists[i1];
                    for (int j3 = 0; j3 < l1; j3++)
                        rasterise(ai[j3]);

                }
            }

            return;
        }
        if (trianglePriorities == null) {
            for (int i1 = diagonal3D - 1; i1 >= 0; i1--) {
                int l1 = depthListIndices[i1];
                if (l1 > 0) {
                    int ai[] = faceLists[i1];//ur dope no ur dope . nou pm me ur pp
                    for (int j3 = 0; j3 < l1; j3++)
                        rasterise(ai[j3]);

                }
            }

            return;
        } // pretty sure this isnt going to work. btw i got the runelite for ruse.
        for (int j1 = 0; j1 < 12; j1++) {
            anIntArray1673[j1] = 0;
            anIntArray1677[j1] = 0;
        }

        for (int i2 = diagonal3D - 1; i2 >= 0; i2--) {
            int k2 = depthListIndices[i2];
            if (k2 > 0) {
                int ai1[] = faceLists[i2];
                for (int i4 = 0; i4 < k2; i4++) {
                    int l4 = ai1[i4];
                    int l5 = facePriorities[l4];
                    int j6 = anIntArray1673[l5]++;
                    anIntArrayArray1674[l5][j6] = l4;
                    if (l5 < 10)
                        anIntArray1677[l5] += i2;
                    else if (l5 == 10)
                        anIntArray1675[j6] = i2;
                    else
                        anIntArray1676[j6] = i2;
                }

            }
        }

        int l2 = 0;
        if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0)
            l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
        int k3 = 0;
        if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0)
            k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
        int j4 = 0;
        if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0)
            j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
        int i6 = 0;
        int k6 = anIntArray1673[10];
        int ai2[] = anIntArrayArray1674[10];
        int ai3[] = anIntArray1675;
        if (i6 == k6) {
            i6 = 0;
            k6 = anIntArray1673[11];
            ai2 = anIntArrayArray1674[11];
            ai3 = anIntArray1676;
        }
        int i5;
        if (i6 < k6)
            i5 = ai3[i6];
        else
            i5 = -1000;
        for (int l6 = 0; l6 < 10; l6++) {
            while (l6 == 0 && i5 > l2) {
                rasterise(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            while (l6 == 3 && i5 > k3) {
                rasterise(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            while (l6 == 5 && i5 > j4) {
                rasterise(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6)
                    i5 = ai3[i6];
                else
                    i5 = -1000;
            }
            int i7 = anIntArray1673[l6];
            int ai4[] = anIntArrayArray1674[l6];
            for (int j7 = 0; j7 < i7; j7++)
                rasterise(ai4[j7]);

        }

        while (i5 != -1000) {
            rasterise(ai2[i6++]);
            if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                i6 = 0;
                ai2 = anIntArrayArray1674[11];
                k6 = anIntArray1673[11];
                ai3 = anIntArray1676;
            }
            if (i6 < k6)
                i5 = ai3[i6];
            else
                i5 = -1000;
        }
    }

    private final void rasterise(int i) {
        if (outOfReach[i]) {
            reduce(i);
            return;
        }
        int j = faceIndicesA[i];
        int k = faceIndicesB[i];
        int l = faceIndicesC[i];
        Rasterizer.aBoolean1462 = hasAnEdgeToRestrict[i];
        if (triangleAlpha == null)
            Rasterizer.anInt1465 = 0;
        else
            Rasterizer.anInt1465 = (triangleAlpha)[i];
        int i1;
        if (triangleInfo == null)
            i1 = 0;
        else
            i1 = triangleInfo[i] & 3;
        if (i1 == 0) {
            Rasterizer.drawGouraudTriangle(projected_vertex_y[j], projected_vertex_y[k], projected_vertex_y[l],
                    projected_vertex_x[j], projected_vertex_x[k], projected_vertex_x[l], face_shade_a[i],
                    face_shade_b[i], face_shade_c[i], projected_vertex_z[j], projected_vertex_z[k],
                    projected_vertex_z[l]);
            return;
        }
        if (i1 == 1) {
            Rasterizer.drawFlatTriangle(projected_vertex_y[j], projected_vertex_y[k], projected_vertex_y[l],
                    projected_vertex_x[j], projected_vertex_x[k], projected_vertex_x[l], hsl2rgb[face_shade_a[i]],
                    projected_vertex_z[j], projected_vertex_z[k], projected_vertex_z[l]);
            return;
        }
        if (i1 == 2) {
            int j1 = triangleInfo[i] >> 2;
            int l1 = textureVertexA[j1];
            int j2 = textureVertexB[j1];
            int l2 = textureVertexC[j1];
            Rasterizer.drawTexturedTriangle(projected_vertex_y[j], projected_vertex_y[k], projected_vertex_y[l],
                    projected_vertex_x[j], projected_vertex_x[k], projected_vertex_x[l], face_shade_a[i],
                    face_shade_b[i], face_shade_c[i], camera_vertex_y[l1], camera_vertex_y[j2], camera_vertex_y[l2],
                    camera_vertex_x[l1], camera_vertex_x[j2], camera_vertex_x[l2], camera_vertex_z[l1],
                    camera_vertex_z[j2], camera_vertex_z[l2], triangleColors[i], projected_vertex_z[j],
                    projected_vertex_z[k], projected_vertex_z[l]);
            return;
        }
        if (i1 == 3) {
            int k1 = triangleInfo[i] >> 2;
            int i2 = textureVertexA[k1];
            int k2 = textureVertexB[k1];
            int i3 = textureVertexC[k1];
            Rasterizer.drawTexturedTriangle(projected_vertex_y[j], projected_vertex_y[k], projected_vertex_y[l],
                    projected_vertex_x[j], projected_vertex_x[k], projected_vertex_x[l], face_shade_a[i],
                    face_shade_a[i], face_shade_a[i], camera_vertex_y[i2], camera_vertex_y[k2], camera_vertex_y[i3],
                    camera_vertex_x[i2], camera_vertex_x[k2], camera_vertex_x[i3], camera_vertex_z[i2],
                    camera_vertex_z[k2], camera_vertex_z[i3], triangleColors[i], projected_vertex_z[j],
                    projected_vertex_z[k], projected_vertex_z[l]);
        }
    }

    private final void reduce(int i) {
        if (triangleColors != null)
            if (triangleColors[i] == 65535)
                return;

        int j = Rasterizer.textureInt1;
        int k = Rasterizer.textureInt2;
        int l = 0;
        int i1 = faceIndicesA[i];
        int j1 = faceIndicesB[i];
        int k1 = faceIndicesC[i];
        int l1 = camera_vertex_z[i1];
        int i2 = camera_vertex_z[j1];
        int j2 = camera_vertex_z[k1];

        if (l1 >= 50) {
            anIntArray1678[l] = projected_vertex_x[i1];
            anIntArray1679[l] = projected_vertex_y[i1];
            anIntArray1680[l++] = face_shade_a[i];
        } else {
            int k2 = camera_vertex_y[i1];
            int k3 = camera_vertex_x[i1];
            int k4 = face_shade_a[i];
            if (j2 >= 50) {
                int k5 = (50 - l1) * lightDecay[j2 - l1];
                anIntArray1678[l] = j
                        + (k2 + ((camera_vertex_y[k1] - k2) * k5 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (k3 + ((camera_vertex_x[k1] - k3) * k5 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = k4 + ((face_shade_c[i] - k4) * k5 >> 16);
            }
            if (i2 >= 50) {
                int l5 = (50 - l1) * lightDecay[i2 - l1];
                anIntArray1678[l] = j
                        + (k2 + ((camera_vertex_y[j1] - k2) * l5 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (k3 + ((camera_vertex_x[j1] - k3) * l5 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = k4 + ((face_shade_b[i] - k4) * l5 >> 16);
            }
        }
        if (i2 >= 50) {
            anIntArray1678[l] = projected_vertex_x[j1];
            anIntArray1679[l] = projected_vertex_y[j1];
            anIntArray1680[l++] = face_shade_b[i];
        } else {
            int l2 = camera_vertex_y[j1];
            int l3 = camera_vertex_x[j1];
            int l4 = face_shade_b[i];
            if (l1 >= 50) {
                int i6 = (50 - i2) * lightDecay[l1 - i2];
                anIntArray1678[l] = j
                        + (l2 + ((camera_vertex_y[i1] - l2) * i6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (l3 + ((camera_vertex_x[i1] - l3) * i6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = l4 + ((face_shade_a[i] - l4) * i6 >> 16);
            }
            if (j2 >= 50) {
                int j6 = (50 - i2) * lightDecay[j2 - i2];
                anIntArray1678[l] = j
                        + (l2 + ((camera_vertex_y[k1] - l2) * j6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (l3 + ((camera_vertex_x[k1] - l3) * j6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = l4 + ((face_shade_c[i] - l4) * j6 >> 16);
            }
        }
        if (j2 >= 50) {
            anIntArray1678[l] = projected_vertex_x[k1];
            anIntArray1679[l] = projected_vertex_y[k1];
            anIntArray1680[l++] = face_shade_c[i];
        } else {
            int i3 = camera_vertex_y[k1];
            int i4 = camera_vertex_x[k1];
            int i5 = face_shade_c[i];
            if (i2 >= 50) {
                int k6 = (50 - j2) * lightDecay[i2 - j2];
                anIntArray1678[l] = j
                        + (i3 + ((camera_vertex_y[j1] - i3) * k6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (i4 + ((camera_vertex_x[j1] - i4) * k6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = i5 + ((face_shade_b[i] - i5) * k6 >> 16);
            }
            if (l1 >= 50) {
                int l6 = (50 - j2) * lightDecay[l1 - j2];
                anIntArray1678[l] = j
                        + (i3 + ((camera_vertex_y[i1] - i3) * l6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1679[l] = k
                        + (i4 + ((camera_vertex_x[i1] - i4) * l6 >> 16)) * WorldController.focalLength / 50;
                anIntArray1680[l++] = i5 + ((face_shade_a[i] - i5) * l6 >> 16);
            }
        }
        int j3 = anIntArray1678[0];
        int j4 = anIntArray1678[1];
        int j5 = anIntArray1678[2];
        int i7 = anIntArray1679[0];
        int j7 = anIntArray1679[1];
        int k7 = anIntArray1679[2];
        if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
            Rasterizer.aBoolean1462 = false;
            if (l == 3) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.viewportRX || j4 > DrawingArea.viewportRX
                        || j5 > DrawingArea.viewportRX)
                    Rasterizer.aBoolean1462 = true;
                int l7;
                if (triangleInfo == null)
                    l7 = 0;
                else
                    l7 = triangleInfo[i] & 3;
                if (l7 == 0)
                    Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
                            anIntArray1680[2], -1, -1, -1);
                else if (l7 == 1)
                    Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, hsl2rgb[face_shade_a[i]], -1, -1, -1);
                else if (l7 == 2) {
                    int j8 = triangleInfo[i] >> 2;
                    int k9 = textureVertexA[j8];
                    int k10 = textureVertexB[j8];
                    int k11 = textureVertexC[j8];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
                            anIntArray1680[2], camera_vertex_y[k9], camera_vertex_y[k10], camera_vertex_y[k11],
                            camera_vertex_x[k9], camera_vertex_x[k10], camera_vertex_x[k11], camera_vertex_z[k9],
                            camera_vertex_z[k10], camera_vertex_z[k11], triangleColors[i], -1, -1, -1);
                } else if (l7 == 3) {
                    int k8 = triangleInfo[i] >> 2;
                    int l9 = textureVertexA[k8];
                    int l10 = textureVertexB[k8];
                    int l11 = textureVertexC[k8];
                    Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, face_shade_a[i], face_shade_a[i],
                            face_shade_a[i], camera_vertex_y[l9], camera_vertex_y[l10], camera_vertex_y[l11],
                            camera_vertex_x[l9], camera_vertex_x[l10], camera_vertex_x[l11], camera_vertex_z[l9],
                            camera_vertex_z[l10], camera_vertex_z[l11], triangleColors[i], -1, -1, -1);
                }
                if (l == 4) {
                    if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.viewportRX || j4 > DrawingArea.viewportRX
                            || j5 > DrawingArea.viewportRX || anIntArray1678[3] < 0
                            || anIntArray1678[3] > DrawingArea.viewportRX)
                        Rasterizer.aBoolean1462 = true;
                    int i8;
                    if (triangleInfo == null)
                        i8 = 0;
                    else
                        i8 = triangleInfo[i] & 3;
                    if (i8 == 0) {
                        Rasterizer.drawGouraudTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
                                anIntArray1680[2], -1, -1, -1);
                        Rasterizer.drawGouraudTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3],
                                anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], -1, -1, -1);
                        return;
                    }
                    if (i8 == 1) {
                        int l8 = hsl2rgb[face_shade_a[i]];
                        Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, l8, -1, -1, -1);
                        Rasterizer.drawFlatTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8, -1, -1,
                                -1);
                        return;
                    }
                    if (i8 == 2) {
                        int i9 = triangleInfo[i] >> 2;
                        int i10 = textureVertexA[i9];
                        int i11 = textureVertexB[i9];
                        int i12 = textureVertexC[i9];

                        Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1],
                                anIntArray1680[2], camera_vertex_y[i10], camera_vertex_y[i11], camera_vertex_y[i12],
                                camera_vertex_x[i10], camera_vertex_x[i11], camera_vertex_x[i12], camera_vertex_z[i10],
                                camera_vertex_z[i11], camera_vertex_z[i12], triangleColors[i], -1, -1, -1);
                        Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3],
                                anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], camera_vertex_y[i10],
                                camera_vertex_y[i11], camera_vertex_y[i12], camera_vertex_x[i10], camera_vertex_x[i11],
                                camera_vertex_x[i12], camera_vertex_z[i10], camera_vertex_z[i11], camera_vertex_z[i12],
                                triangleColors[i], -1, -1, -1);
                        return;
                    }
                    if (i8 == 3) {
                        int j9 = triangleInfo[i] >> 2;
                        int j10 = textureVertexA[j9];
                        int j11 = textureVertexB[j9];
                        int j12 = textureVertexC[j9];
                        Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5, face_shade_a[i], face_shade_a[i],
                                face_shade_a[i], camera_vertex_y[j10], camera_vertex_y[j11], camera_vertex_y[j12],
                                camera_vertex_x[j10], camera_vertex_x[j11], camera_vertex_x[j12], camera_vertex_z[j10],
                                camera_vertex_z[j11], camera_vertex_z[j12], triangleColors[i], -1, -1, -1);
                        Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3],
                                face_shade_a[i], face_shade_a[i], face_shade_a[i], camera_vertex_y[j10],
                                camera_vertex_y[j11], camera_vertex_y[j12], camera_vertex_x[j10], camera_vertex_x[j11],
                                camera_vertex_x[j12], camera_vertex_z[j10], camera_vertex_z[j11], camera_vertex_z[j12],
                                triangleColors[i], -1, -1, -1);
                    }
                }
            }
        }
    }

    private final boolean cursorOn(int cursorX, int cursorY, int proj_vertex_yA, int proj_vertex_yB, int proj_vertex_yC,
            int proj_vertex_xA, int proj_vertex_xB, int proj_vertex_xC) {
        if (cursorY < proj_vertex_yA && cursorY < proj_vertex_yB && cursorY < proj_vertex_yC)
            return false;
        if (cursorY > proj_vertex_yA && cursorY > proj_vertex_yB && cursorY > proj_vertex_yC)
            return false;
        if (cursorX < proj_vertex_xA && cursorX < proj_vertex_xB && cursorX < proj_vertex_xC)
            return false;
        return cursorX <= proj_vertex_xA || cursorX <= proj_vertex_xB || cursorX <= proj_vertex_xC;
    }

    public void reset() {
        verticesXCoordinate = null;
        verticesYCoordinate = null;
        verticesZCoordinate = null;
        faceIndicesA = null;
        faceIndicesB = null;
        faceIndicesC = null;
        face_shade_a = null;
        face_shade_b = null;
        face_shade_c = null;
        triangleInfo = null;
        facePriorities = null;
        trianglePriorities = null;
        triangleAlpha = null;
        triangleColors = null;
        textureVertexA = textureVertexB = textureVertexC = null;
        vertexLabels = null;
        triangleLabels = null;
        groupedLabels = null;
        groupedTriangleLabels = null;
        vertexNormalOffset = null;
    }

    private boolean aBoolean1618;
    public static int anInt1620;
    public static Model entityModelDesc = new Model(true);
    protected static int tmpParticlesArray[] = new int[20000];
    protected static int anIntArray1622[] = new int[20000];
    protected static int anIntArray1623[] = new int[20000];
    protected static int anIntArray1624[] = new int[20000];
    protected static int anIntArray1625[] = new int[20000];
    public int vertexCount;
    public int[] verticesXCoordinate;
    public int[] verticesYCoordinate;
    public int[] verticesZCoordinate;
    public int triangleCount;
    public int[] faceIndicesA;
    public int[] faceIndicesB;
    public int[] faceIndicesC;
    public int[] face_shade_a;
    public int[] face_shade_b;
    public int[] face_shade_c;
    public int[] triangleInfo;
    public int[] trianglePriorities; // done
    public int[] facePriorities;
    public int[] triangleAlpha;
    public int[] triangleColors;
    public int face_priority;
    public int texturedFaces;
    public int textureVertexA[];
    public int textureVertexB[];
    public int textureVertexC[];
    public int anInt1646;
    public int anInt1647;
    public int anInt1648;
    public int anInt1649;
    public int anInt1650;
    public int anInt1651;
    public int diagonal3D;
    public int anInt1653;
    public int myPriority;
    public int vertexLabels[];
    public int triangleLabels[];
    public int groupedLabels[][];
    public int groupedTriangleLabels[][];
    public boolean rendersWithinOneTile;
    VertexNormal vertexNormalOffset[];
    static ModelHeader modelHeader[];
    static OnDemandFetcherParent resourceManager;
    static boolean hasAnEdgeToRestrict[] = new boolean[MAX_POLYGON];
    static boolean outOfReach[] = new boolean[MAX_POLYGON];
    static int projected_vertex_x[] = new int[MAX_POLYGON];
    static int projected_vertex_y[] = new int[25000];
    static int projected_vertex_z[] = new int[25000];
    static int vertexPerspectiveDepth[] = new int[MAX_POLYGON];
    static int camera_vertex_y[] = new int[MAX_POLYGON];
    static int camera_vertex_x[] = new int[MAX_POLYGON];
    static int camera_vertex_z[] = new int[MAX_POLYGON];
    static int depthListIndices[] = new int[1500];
    static int faceLists[][] = new int[1500][512];
    static int anIntArray1673[] = new int[12];
    static int anIntArrayArray1674[][] = new int[12][20000];
    static int anIntArray1675[] = new int[20000];
    static int anIntArray1676[] = new int[20000];
    static int anIntArray1677[] = new int[12];
    static int anIntArray1678[] = new int[10];
    static int anIntArray1679[] = new int[10];
    static int anIntArray1680[] = new int[10];
    static int anInt1681;
    static int anInt1682;
    static int anInt1683;
    public static boolean objectExists;
    public static int lineOffsets[];
    public static int currentCursorX;
    public static int currentCursorY;
    public static int objectsRendered;
    public static int objectsInCurrentRegion[] = new int[1000];
    public static int mapObjectIds[] = new int[1000];
    public static int SINE[];
    public static int COSINE[];
    static int hsl2rgb[];
    static int lightDecay[];

    static {
        SINE = Rasterizer.anIntArray1470;
        COSINE = Rasterizer.anIntArray1471;
        hsl2rgb = Rasterizer.anIntArray1482;
        lightDecay = Rasterizer.anIntArray1469;
    }
}